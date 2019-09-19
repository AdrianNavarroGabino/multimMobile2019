// Adrián Navarro Gabino

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

namespace SpaceHawks
{
    public class Game1 : Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;

        const int GAME = 0;
        const int WELCOME = 1;

        private Texture2D background;

        private Texture2D ship;
        private Vector2 shipPosition;
        private float shipSpeed = 400;

        private Texture2D enemy;
        private Vector2 enemyPosition;
        private Vector2 enemySpeed;

        private SpriteFont font;

        private Song backgroundMusic;
        private SoundEffect playerDie;
        private SoundEffect fire;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";

            graphics.PreferredBackBufferWidth = 960;
            graphics.PreferredBackBufferHeight = 600;
            graphics.ApplyChanges();
        }

        protected override void Initialize()
        {
            base.Initialize();

            shipPosition = new Vector2(400, 500);

            enemyPosition = new Vector2(300, 100);
            enemySpeed = new Vector2(150, 100);
        }

        protected override void LoadContent()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);

            background = Content.Load<Texture2D>("sprites/fondo960");
            ship = Content.Load<Texture2D>("sprites/nave");
            enemy = Content.Load<Texture2D>("sprites/enemigo1a");

            font = Content.Load<SpriteFont>("fonts/Font8Bit");

            backgroundMusic = Content.Load<Song>("sounds/levelTick");
            playerDie = Content.Load<SoundEffect>("sounds/playerDie");
            fire = Content.Load<SoundEffect>("sounds/fire");

            MediaPlayer.Play(backgroundMusic);
            MediaPlayer.IsRepeating = true;
        }

        protected override void Update(GameTime gameTime)
        {
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back ==
                ButtonState.Pressed ||
                Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();

            var keyboardState = Keyboard.GetState();

            if (keyboardState.IsKeyDown(Keys.Left) && (shipPosition.X > 20))
                shipPosition.X -= shipSpeed *
                    (float)gameTime.ElapsedGameTime.TotalSeconds;
            if (keyboardState.IsKeyDown(Keys.Right) && (shipPosition.X < 850))
                shipPosition.X += shipSpeed *
                    (float)gameTime.ElapsedGameTime.TotalSeconds;
            if (keyboardState.IsKeyDown(Keys.Space))
                fire.Play();

            enemyPosition.X += enemySpeed.X *
                (float)gameTime.ElapsedGameTime.TotalSeconds;
            enemyPosition.Y += enemySpeed.Y *
                (float)gameTime.ElapsedGameTime.TotalSeconds;

            if ((enemyPosition.X < 20) || (enemyPosition.X > 850))
                enemySpeed.X = -enemySpeed.X;
            if ((enemyPosition.Y < 20) || (enemyPosition.Y > 550))
                enemySpeed.Y = -enemySpeed.Y;

            // Collisions checking
            if (new Rectangle((int)shipPosition.X, (int)shipPosition.Y,
                ship.Width, ship.Height).Intersects(
                new Rectangle(
                    (int)enemyPosition.X, (int)enemyPosition.Y,
                    enemy.Width, enemy.Height)))
            {
                playerDie.Play();
            }

            base.Update(gameTime);
        }

        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.Black);

            spriteBatch.Begin();
            spriteBatch.Draw(background, new Rectangle(
                0,
                0,
                graphics.PreferredBackBufferWidth,
                graphics.PreferredBackBufferHeight),
                Color.White);
            spriteBatch.DrawString(font, "Hello", new Vector2(50, 10), Color.Yellow);
            spriteBatch.Draw(ship, new Rectangle(
                (int)shipPosition.X, (int)shipPosition.Y,
                ship.Width, ship.Height),
                Color.White);
            spriteBatch.Draw(enemy, new Rectangle(
                (int)enemyPosition.X, (int)enemyPosition.Y,
                enemy.Width, enemy.Height),
                Color.White);
            spriteBatch.End();

            base.Draw(gameTime);
        }
    }
}


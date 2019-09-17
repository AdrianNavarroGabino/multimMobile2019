// Adrián Navarro Gabino

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace SpaceHawks
{
    public class Game1 : Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;

        private Texture2D background;

        private Texture2D ship;
        private Vector2 shipPosition;

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
        }
        
        protected override void LoadContent()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);

            background = Content.Load<Texture2D>("sprites/fondo960");
            ship = Content.Load<Texture2D>("sprites/nave");
        }

        protected override void Update(GameTime gameTime)
        {
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();

            // TODO: Add your update logic here

            base.Update(gameTime);
        }
        
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.Black);

            spriteBatch.Begin();
            spriteBatch.Draw(background, new Rectangle(0, 0, 960, 600), Color.White);
            spriteBatch.Draw(ship, new Rectangle((int)shipPosition.X, (int)shipPosition.Y, ship.Width, ship.Height), Color.White);
            spriteBatch.End();

            base.Draw(gameTime);
        }
    }
}

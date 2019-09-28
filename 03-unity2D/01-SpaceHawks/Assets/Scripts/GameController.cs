using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameController : MonoBehaviour
{
    [SerializeField] private int score;
    [SerializeField] private int lives;
    [SerializeField] private Text livesText;
    [SerializeField] private Text scoreText;
    [SerializeField] private Text gameOverText;
    [SerializeField] private Text levelText;
    [SerializeField] Transform prefabEnemy;
    [SerializeField] const int NUMBER_OF_ENEMIES = 24;
    [SerializeField] const int ENEMY_ROWS = 4;
    [SerializeField] const int ENEMY_COLUMNS = 6;
    [SerializeField] private Vector2 initialEnemyPosition;
    [SerializeField] public int level;
    private static int trickCount = 0;

    private Enemy[] enemies;
    public int numberOfLivingEnemies;

    // Start is called before the first frame update
    void Start()
    {
        score = 0;
        lives = 3;
        DisplayText();
        gameOverText.enabled = false;
        enemies = new Enemy[NUMBER_OF_ENEMIES];
        initialEnemyPosition = new Vector2(-1.732229f, 1.884993f);
        level = 1;
        StartCoroutine(CreateEnemies());
        numberOfLivingEnemies = NUMBER_OF_ENEMIES;
    }

    public void DisplayText()
    {
        livesText.text = "Lives: " + lives;
        scoreText.text = "Score: " + score;
    }

    public void LoseLife()
    {
        lives--;
        DisplayText();
        if (lives <= 0)
        {
            StartCoroutine(GameOver());
        }
    }

    public void KillEnemy()
    {
        score += 10;
        DisplayText();
        numberOfLivingEnemies--;

        if (numberOfLivingEnemies == 0)
        {
            level++;
            Enemy.speedX *= 1.3f;
            Enemy.speedY += 0.02f;
            StartCoroutine(CreateEnemies());
            numberOfLivingEnemies = NUMBER_OF_ENEMIES;
        }
    }

    public IEnumerator GameOver()
    {
        gameOverText.enabled = true;
        Time.timeScale = 0.01f;
        yield return new WaitForSecondsRealtime(3);
        Time.timeScale = 1;
        SceneManager.LoadScene(0);
    }

    public IEnumerator CreateEnemies()
    {
        // Time for the enemy to explode
        if (level != 1)
            yield return new WaitForSeconds(1);
        levelText.text = "Level " + level;
        levelText.enabled = true;
        Time.timeScale = 0.01f;
        yield return new WaitForSecondsRealtime(3);
        Time.timeScale = 1;
        levelText.enabled = false;
        for (int i = 0; i < ENEMY_ROWS; i++)
        {
            for(int j = 0; j < ENEMY_COLUMNS; j++)
            {
                Transform enemy = Instantiate(prefabEnemy,
                new Vector2(initialEnemyPosition.x + 0.9f * j,
                            initialEnemyPosition.y - 0.4f * i),
                Quaternion.identity);
            }
        }
    }

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.T))
        {
            lives++;
            DisplayText();
        }

        if (Input.GetKeyDown(KeyCode.K))
        {
            Destroy(FindObjectOfType<Enemy>().gameObject);
            KillEnemy();
        }

        if (Input.GetKeyDown(KeyCode.Alpha9))
        {
            while (FindObjectOfType<Enemy>())
            {
                Destroy(FindObjectOfType<Enemy>().gameObject);
                KillEnemy();
            }
        }
    }
}

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

    // Start is called before the first frame update
    void Start()
    {
        score = 0;
        lives = 3;
        DisplayText();
        gameOverText.enabled = false;
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
    }

    IEnumerator GameOver()
    {
        gameOverText.enabled = true;
        Time.timeScale = 0.01f;
        yield return new WaitForSecondsRealtime(3);
        Time.timeScale = 1;
        SceneManager.LoadScene(0);
    }
}

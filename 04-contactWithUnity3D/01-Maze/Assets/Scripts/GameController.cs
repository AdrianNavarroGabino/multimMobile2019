using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameController : MonoBehaviour
{
    [SerializeField] private float initialTime;
    [SerializeField] private Text timeText;
    [SerializeField] private Text gameOverText;

    // Start is called before the first frame update
    void Start()
    {
        gameOverText.enabled = false;
        initialTime = 30;
    }

    // Update is called once per frame
    void Update()
    {
        timeText.text = "Time: " + initialTime;
        initialTime -= Time.deltaTime;

        if(initialTime <= 0)
        {
            initialTime = 0;
            StartCoroutine(GameOver());
        }
    }

    public IEnumerator GameOver()
    {
        gameOverText.enabled = true;
        Time.timeScale = 0.01f;
        yield return new WaitForSecondsRealtime(3);
        Time.timeScale = 1f;
        SceneManager.LoadScene(0);
    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameStatus : MonoBehaviour
{
    public int score = 0;
    public int lives = 3;
    public int currentLevel = 1;

    private void Awake()
    {
        int gameStatusCount = FindObjectsOfType<GameStatus>().Length;

        if(gameStatusCount > 1)
        {
            Destroy(gameObject);
        }
        else
        {
            DontDestroyOnLoad(gameObject);
        }
    }
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void LevelUp()
    {
        currentLevel++;
    }

    public void ScoreUp()
    {
        score += 10;
    }
    public void LivesDown()
    {
        lives--;
    }
}

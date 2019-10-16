using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayAgain : MonoBehaviour
{
    private GameStatus gs;

    // Start is called before the first frame update
    void Start()
    {
        gs = FindObjectOfType<GameStatus>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void Play()
    {
        gs.lives = 3;
        gs.score = 0;
        gs.currentLevel = 1;
        SceneManager.LoadScene("Level1");
    }

    public void Play1()
    {
        SceneManager.LoadScene("Level1");
    }
}

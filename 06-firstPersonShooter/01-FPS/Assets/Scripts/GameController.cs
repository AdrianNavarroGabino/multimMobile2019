using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour
{
    private int remainingEnemies;

    // Start is called before the first frame update
    void Start()
    {
        remainingEnemies = FindObjectsOfType<Enemy>().Length;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void EnemyDies()
    {
        remainingEnemies--;
        if(remainingEnemies == 0)
        {
            SceneManager.LoadScene("Level2");
        }
    }
}

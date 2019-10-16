using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Key : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            Destroy(gameObject);
            int level = FindObjectOfType<GameStatus>().currentLevel;
            FindObjectOfType<GameStatus>().SendMessage("LevelUp");
            if (SceneManager.sceneCountInBuildSettings - 3 > level)
            {
                SceneManager.LoadScene("Level" + (level + 1));
            }
            else
            {
                SceneManager.LoadScene("Congratulations");
            }
        }
    }
}

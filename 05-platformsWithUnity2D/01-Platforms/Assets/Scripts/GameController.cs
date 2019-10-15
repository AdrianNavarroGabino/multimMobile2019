using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameController : MonoBehaviour
{
    private int score;
    private int lives;
    private int remainingItems;
    private Key key;
    private GameStatus gs;

    // Start is called before the first frame update
    void Start()
    {
        gs = FindObjectOfType<GameStatus>();
        key = FindObjectOfType<Key>();
        key.gameObject.SetActive(false);
        score = gs.score;
        lives = gs.lives;
        remainingItems = FindObjectsOfType<Diamond>().Length;
        Debug.Log("Remaining items: " + remainingItems);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.T))
        {
            key.gameObject.SetActive(true);
        }
    }

    public void ItemPicked()
    {
        remainingItems--;
        Debug.Log("Remaining items: " + remainingItems);
        score += 10;
        Debug.Log("Score: " + score);
        gs.SendMessage("ScoreUp");

        if(remainingItems == 0)
        {
            key.gameObject.SetActive(true);
        }
    }
}

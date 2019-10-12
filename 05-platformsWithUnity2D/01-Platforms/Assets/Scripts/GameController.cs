using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour
{
    private int score;
    private int remainingItems;

    // Start is called before the first frame update
    void Start()
    {
        score = 0;
        remainingItems = FindObjectsOfType<Diamond>().Length;
        Debug.Log("Remaining items: " + remainingItems);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void ItemPicked()
    {
        remainingItems--;
        Debug.Log("Remaining items: " + remainingItems);
        score += 10;
        Debug.Log("Score: " + score);
    }
}

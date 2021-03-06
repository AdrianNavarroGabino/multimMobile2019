﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Diamond : MonoBehaviour
{
    public static int score;
    public static int remainingItems;
    [SerializeField] AudioClip audio;
    
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
        if (other.tag == "Player")
        {
            Destroy(gameObject);
            FindObjectOfType<GameController>().
                SendMessage("ItemPicked");

            AudioSource.PlayClipAtPoint(audio, Camera.main.transform.position);
        }
    }
}

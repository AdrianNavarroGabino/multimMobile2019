using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [SerializeField]private float speedX = 2F;
    [SerializeField] private float speedY = 2F;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(
            speedX * Time.deltaTime, speedY * Time.deltaTime, 0);
        if (transform.position.x > 4 || transform.position.x < -4)
            speedX = -speedX;
        if (transform.position.y > 2.5 || transform.position.y < -2.5)
            speedY = -speedY;
    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyShot : MonoBehaviour
{
    [SerializeField] private float speed = -200;
    private GameController g;

    private void Start()
    {
        gameObject.GetComponent<Rigidbody2D>().velocity =
            new Vector2(0, speed * Time.deltaTime);
        g = FindObjectOfType<GameController>();
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.position.y < -5)
        {
            Destroy(gameObject);
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Spaceship")
        {
            Destroy(gameObject);

            g.LoseLife();
        }
    }
}

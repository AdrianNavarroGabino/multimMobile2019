using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Shot : MonoBehaviour
{
    [SerializeField] private float speed = 200;
    [SerializeField] private Transform explosionPrefab;
    private GameController g;

    private void Start()
    {
        gameObject.GetComponent<Rigidbody2D>().velocity =
            new Vector2(0, speed * Time.deltaTime);
        g = g = FindObjectOfType<GameController>(); ;
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.position.y > 5)
        {
            Destroy(gameObject);
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Enemy")
        {
            Transform explosion = Instantiate(explosionPrefab,
                transform.position,
                Quaternion.identity);
            Destroy(other.gameObject);
            Destroy(gameObject);
            Destroy(explosion.gameObject, 1f);
            g.KillEnemy();
        }
    }
}

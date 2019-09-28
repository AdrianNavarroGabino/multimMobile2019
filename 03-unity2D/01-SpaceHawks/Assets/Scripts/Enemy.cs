using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [SerializeField] public static float speedX = 1F;
    [SerializeField] public static float speedY = 0.08f;
    [SerializeField] Transform prefabShot;
    private GameController g;

    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(Shoot());
        g = FindObjectOfType<GameController>();
    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(
            speedX * Time.deltaTime, -speedY * Time.deltaTime, 0);
        if (transform.position.x > 4 || transform.position.x < -4)
        {
            speedX = -speedX;
        }

        if(transform.position.y < -3)
        {
            StartCoroutine(g.GameOver());
        }
    }

    IEnumerator Shoot()
    {
        float pause = Random.Range(5, 15);
        yield return new WaitForSeconds(pause);
        Transform shot = Instantiate(prefabShot,
            transform.position, Quaternion.identity);
        StartCoroutine(Shoot());
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Spaceship")
        {
            g.LoseLife();
        }
    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [SerializeField] private float speedX = 2F;
    [SerializeField] private float speedY = 2F;
    [SerializeField] private float shotSpeed = -2F;
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
            speedX * Time.deltaTime, speedY * Time.deltaTime, 0);
        if (transform.position.x > 4 || transform.position.x < -4)
            speedX = -speedX;
        if (transform.position.y > 2.5 || transform.position.y < -2.5)
            speedY = -speedY;
    }

    IEnumerator Shoot()
    {
        float pause = Random.Range(3, 7);
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

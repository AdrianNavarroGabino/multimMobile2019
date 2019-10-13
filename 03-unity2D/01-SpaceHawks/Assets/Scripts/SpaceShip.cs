using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SpaceShip : MonoBehaviour
{
    [SerializeField] private float speed = 50;
    [SerializeField] private Text scoreboardText;
    [SerializeField] Transform prefabShot;
    private GameController g;
    private bool canGoLeft;
    private bool canGoRight;

    // Start is called before the first frame update
    void Start()
    {
        g = FindObjectOfType<GameController>();
        canGoLeft = true;
        canGoRight = true;
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal");

        if (g.IsInputEnabled())
        {
            if (horizontal < 0 && canGoLeft)
            {
                canGoRight = true;
                transform.Translate(horizontal * speed * Time.deltaTime, 0, 0);
            }
            else if(horizontal > 0 && canGoRight)
            {
                canGoLeft = true;
                transform.Translate(horizontal * speed * Time.deltaTime, 0, 0);
            }

            if (Input.GetButtonDown("Fire1"))
            {
                GetComponent<AudioSource>().Play();
                Transform shot = Instantiate(prefabShot,
                    new Vector2(transform.position.x,
                                transform.position.y + (float)0.4),
                    Quaternion.identity);
            }
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "LeftLimit")
        {
            canGoLeft = false;
        }
        if(other.tag == "RightLimit")
        {
            canGoRight = false;
        }
    }
}

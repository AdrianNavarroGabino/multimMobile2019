using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpaceShip : MonoBehaviour
{
    [SerializeField] private float speed = 50;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal");
        
        transform.Translate(horizontal * speed * Time.deltaTime, 0, 0);

        if (Input.GetButtonDown("Fire1"))
        {
            GetComponent<AudioSource>().Play();
        }
    }
}

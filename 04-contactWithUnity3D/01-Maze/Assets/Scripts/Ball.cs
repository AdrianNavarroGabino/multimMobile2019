using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour
{
    private Rigidbody body;
    float speed = 150f;
    float rotationSpeed = 150f;
    private static int items;
    private Vector3 initialPosition;

    // Start is called before the first frame update
    void Start()
    {
        initialPosition = transform.position;
        body = GetComponent<Rigidbody>();
        items = 0;
    }

    // Update is called once per frame
    void Update()
    {
        float advance = Input.GetAxis("Vertical") * speed * Time.deltaTime;
        float rotation = Input.GetAxis("Horizontal") * rotationSpeed * Time.deltaTime;
        transform.Rotate(Vector3.up, rotation);
        transform.position += transform.forward * Time.deltaTime * advance;
    }

    private void OnTriggerEnter(Collider other)
    {
        /*Debug.Log("Tocado");
        Application.Quit();*/
        if(other.tag == "Item")
        {
            Destroy(other.gameObject);
            items++;
            
            if(items == 3)
            {
                Destroy(FindObjectOfType<FalseWall>().gameObject);
            }
        }
        if(other.tag == "Enemy")
        {
            transform.position = initialPosition;
        }
    }
}

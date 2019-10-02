using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour
{
    private Rigidbody body;
    float speed = 150f;
    float rotationSpeed = 150f;

    // Start is called before the first frame update
    void Start()
    {
        body = GetComponent<Rigidbody>();
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
        Debug.Log("Tocado");
        Application.Quit();
    }
}

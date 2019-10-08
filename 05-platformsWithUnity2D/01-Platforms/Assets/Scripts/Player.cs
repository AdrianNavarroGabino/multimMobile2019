using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    [SerializeField] private float speed = 4;
    [SerializeField] private float jumpSpeed = 2;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal");

        transform.Translate(horizontal * speed * Time.deltaTime, 0, 0);

        float jump = Input.GetAxis("Jump");

        if (jump > 0)
        {
            Vector3 jumpForce = new Vector3(0, jumpSpeed, 0);
            GetComponent<Rigidbody2D>().AddForce(jumpForce);
        }
    }
}

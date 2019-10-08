using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    [SerializeField] private float speed = 3;
    [SerializeField] private float jumpSpeed = 8;
    private bool canJump;

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

        if (jump > 0 && canJump)
        {
            canJump = false;
            Vector3 jumpForce = new Vector3(0, jumpSpeed, 0);
            GetComponent<Rigidbody2D>().AddForce(jumpForce);
        }
    }

    private void OnCollisionEnter2D(Collision2D other)
    {
        if(other.collider.tag == "Platform")
        {
            canJump = true;
        }
    }
}

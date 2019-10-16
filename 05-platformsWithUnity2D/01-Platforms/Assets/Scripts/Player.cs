using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    [SerializeField] private float speed = 2;
    [SerializeField] private float jumpSpeed = 200;
    private Vector3 initialPosition;
    private float height;
    Animator animator;

    // Start is called before the first frame update
    void Start()
    {
        height = GetComponent<Collider2D>().bounds.size.y;
        initialPosition = transform.position;
        animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal");

        if (horizontal != 0)
        {
            animator.Play("PlayerWalking");
            transform.Translate(horizontal * speed * Time.deltaTime, 0, 0);
        }
        else
        {
            animator.Play("PlayerStatic");
        }

        float jump = Input.GetAxisRaw("Jump");

        if (jump > 0)
        {
            RaycastHit2D hit =
                Physics2D.Raycast(transform.position, new Vector2(0, -1));
            float floorDistance = hit.distance;
            bool grounded = floorDistance < height * 0.6f;
            if (grounded)
            {
                Vector2 jumpForce = new Vector2(0, jumpSpeed);
                GetComponent<Rigidbody2D>().AddForce(jumpForce);
            }
        }
    }

    void Reset()
    {
        transform.position = initialPosition;
    }
}

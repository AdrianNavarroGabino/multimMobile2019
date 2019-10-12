using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    [SerializeField] private float speed = 2;
    [SerializeField] private float jumpSpeed = 200;
    private Vector3 initialPosition;
    private float height;

    // Start is called before the first frame update
    void Start()
    {
        height = GetComponent<Collider2D>().bounds.size.y;
        initialPosition = transform.position;
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal");

        transform.Translate(horizontal * speed * Time.deltaTime, 0, 0);

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

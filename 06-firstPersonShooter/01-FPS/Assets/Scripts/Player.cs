using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    [SerializeField] Camera camera;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        bool shootHit = false;

        if (Input.GetButtonDown("Fire1"))
        {
            Debug.Log("Shooting up...");
            float maxDistance = 100;
            RaycastHit objectHit;
            shootHit = Physics.Raycast(camera.transform.position,
                camera.transform.forward, out objectHit, maxDistance);

            if (shootHit)
            {
                Debug.Log("Shocked Shock");
                if (objectHit.collider.CompareTag("Enemy"))
                {
                    Debug.Log("Successful enemy");
                }
            }
        }
    }
}

using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class Player : MonoBehaviour
{
    [SerializeField] private Camera camera;
    [SerializeField] private TMP_Text scoreText;
    [SerializeField] private TMP_Text bulletsText;
    private int score, bullets;

    // Start is called before the first frame update
    void Start()
    {
        score = 0;
        bullets = 20;
        scoreText.text = "Score: " + score;
        bulletsText.text = "" + bullets;
    }

    // Update is called once per frame
    void Update()
    {
        bool shootHit = false;

        if (Input.GetButtonDown("Fire1") && bullets > 0)
        {
            bullets--;
            bulletsText.text = "" + bullets;
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
                    score++;
                    scoreText.text = "Score: " + score;
                    FindObjectOfType<Enemy>().SendMessage("Hit");
                }
            }
        }
    }

    public void MoreBullets()
    {
        bullets += 10;
        bulletsText.text = "" + bullets;
    }
}

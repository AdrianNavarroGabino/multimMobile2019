using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class Enemy : MonoBehaviour
{
    [SerializeField] Transform objective;
    NavMeshAgent navMeshAgent;
    private int lives;
    private GameController gs;

    // Start is called before the first frame update
    void Start()
    {
        navMeshAgent = GetComponent<NavMeshAgent>();
        lives = 2;
        gs = FindObjectOfType<GameController>();
    }

    // Update is called once per frame
    void Update()
    {
        navMeshAgent.SetDestination(objective.position);
    }

    public void Hit()
    {
        lives--;
        if(lives == 0)
        {
            Destroy(gameObject);
            gs.SendMessage("EnemyDies");
        }
    }
}

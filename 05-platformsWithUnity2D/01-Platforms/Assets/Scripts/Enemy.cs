using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [SerializeField] Transform[] wayPoints;
    [SerializeField] private float speed = 1f;
    private Vector3 nextPosition;
    private int nextPositionNumber = 0;
    private float changeDistance = 0.1f;
    // Start is called before the first frame update
    void Start()
    {
        nextPosition = wayPoints[nextPositionNumber].position;
    }

    // Update is called once per frame
    void Update()
    {
        transform.position = Vector3.MoveTowards(
            transform.position,
            nextPosition,
            speed * Time.deltaTime);

        if (Vector3.Distance(transform.position,
            nextPosition) < changeDistance)
        {
            nextPositionNumber++;
            if (nextPositionNumber >= wayPoints.Length)
            {
                nextPositionNumber = 0;
            }
            nextPosition = wayPoints[nextPositionNumber].position;
        }
    }
}

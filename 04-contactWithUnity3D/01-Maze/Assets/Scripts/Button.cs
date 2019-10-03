using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Button : MonoBehaviour
{
    public void RunGame()
    {
        SceneManager.LoadScene("Level1");
    }
}

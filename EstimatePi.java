/**
 * The purpose of EstimatePi is to apply the Monte Carlo Method to the number of darts and trials, which allows for an estimate of pi due to the (x,y).  
 *
 * @author Briana Berger
 * @version 12/26/2017
 */

import java.util.Random;
import java.util.Scanner;
public class EstimatePi
{
    //Calculate hits per trial
    public static double[] calculateHitPerAttempt(int t, int d)
    {
       double hitsPerAttempts [] = new double [d];
       Random r = new Random();
       
       //The outer loop is purposed for the program to go through each trial. Then, the inner loop is purposed to throw all the assigned darts per trial.
       for (int i = 0; i < t; i++)
       {
           int hits = 0;
           for(int j = 0; j < d; j++)
           {
               double x = r.nextDouble();
               double y = r.nextDouble();
               
               //Used to count up the number of hits that occur. We don't need to calculate misses as all we need are hits and total attempts (& total attempts comes from the number of darts)
               if(Math.pow(x, 2) + Math.pow(y, 2) <= 1)
               {
                   hits++;
               }
           }
           
           //Each trial's number of attempts is the number of darts. So, we calculate the number of hits per attempts as we will need it for estimating pi.
           hitsPerAttempts[i] = Math.round(((double)hits/d)*1000000.0)/1000000.0;
       }
       return hitsPerAttempts;
    }
    
    //Estimate pi in an array
    public static double [] calculatePi(double h[], int t)
    {
        double [] piArray = new double [t];
        double pi = 0;
        //Estimates pi based on our values from the array hitsPerAttempts and trials. 
        for(int i = 0; i < t; i++)
        {
            pi = 4 * h[i];
            piArray[i] = pi;
        }
        return piArray;
    }
    
    //Print results
    public static void printResults(int t, double p[])
    {
        double totalPi = 0;
        double piOverallEst = 0;
        int count = 1;
        //Goes through each trial and produces what each trial produced as an est for pi. We also count up the total pi value through all the trials for our final estimate.
        for(int i = 0; i < t; i++)
        {
            System.out.printf("%s %s %s %2s %n", "Trial [", count, "]: pi =", p[i]);
            totalPi += p[i];
            count++;
        }
        
        //Estimates pi based on all our trials' pi values.
        piOverallEst = Math.round(((double)totalPi/t) * 1000000.0)/1000000.0;
        System.out.printf("%s %6s %n", "Estimate of pi =", piOverallEst);
    }
    
    //Main Method
    public static void main (String [ ] args)
    {
       Scanner in = new Scanner(System.in);
       
       int trials;
       int darts;
       
       //Get user information.
       System.out.printf("How many darts per trial? ");
       darts = in.nextInt();
       System.out.printf("How many trials? " );
       trials = in.nextInt();
       
       //Uses print results method while also using the calculatePi method (which then uses the calculateHitPerAttempt method)
       printResults(trials, calculatePi(calculateHitPerAttempt(trials, darts), trials));
    }
}

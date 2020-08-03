using System.ComponentModel.DataAnnotations;
using System.Diagnostics;
using System.Numerics;
using System.Threading.Tasks;
using System.Linq;
using System;
using ShellProgressBar;

namespace RSA_Evaluation
{
  public class Evaluation
  {
    private readonly uint PublicKey, PrivateKey, N, Salt2, Salt1;

    private const int CollisionChecks = short.MaxValue * 64;

    public Evaluation(uint publicKey, uint privateKey, uint n, uint salt1, uint salt2)
    {
      PublicKey = publicKey;
      PrivateKey = privateKey;
      N = n;
      Salt1 = salt1;
      Salt2 = salt2;
    }

    public bool Evaluate()
    {
      return CheckEncryption() &&
      CheckCollisions();
    }

    private bool CheckEncryption()
    {
      var max = 1000;
      using (var pb = new ProgressBar(max, "Testing encryption matches decryption", new ProgressBarOptions()
      {
        ShowEstimatedDuration = true
      }))
      {
        for (uint i = 0; i < max; i++)
        {
          if (Decrypt(Encrypt(i)) != i)
          {
            pb.Tick(pb.MaxTicks, $"{i} was not successfully decrypted");
            Console.WriteLine(Encrypt(i));
            Console.WriteLine($"{i} =\\= {Decrypt(Encrypt(i))}");
            return false;
          }
          pb.Tick();

        }
      }
      return true;
    }

    private bool CheckCollisions()
    {
      BigInteger[] numbers = new BigInteger[CollisionChecks];
      Task[] tasks = new Task[Environment.ProcessorCount];

      var options = new ProgressBarOptions
      {
        ProgressBarOnBottom = true,
        ShowEstimatedDuration = true
      };


      using (var pb = new ProgressBar((int)CollisionChecks, "Checking for collisions", options))
      {
        //Launch tasks to fill each item
        for (int i = 0; i < Environment.ProcessorCount; i++)
        {
          int id = i + 0;
          tasks[i] = Task.Run(() => PopulateArray(ref numbers, pb, id));
          System.Threading.Thread.Sleep(150);
        }


        //Wait for fill
        foreach (var t in tasks)
          t.Wait();

      }

      var collisions = CollisionChecks - numbers.AsParallel().Distinct().Count();
      Console.WriteLine($"There were {collisions} collisions\t({collisions / (float)CollisionChecks * 100}%)");

      //If all are unique return true
      return collisions == 0;
    }

    private void PopulateArray(ref BigInteger[] numbers, ProgressBar progress, int id)
    {
      ProgressBarOptions childOptions = new ProgressBarOptions
      {
        ForegroundColor = ConsoleColor.Green,
        BackgroundColor = ConsoleColor.DarkGreen,
        ProgressCharacter = '─',
        ProgressBarOnBottom = true,
        ShowEstimatedDuration = true
      };
      using (var child = progress.Spawn((int)(CollisionChecks / Environment.ProcessorCount), "Encryption thread " + id, childOptions))
      {
        DateTime start = DateTime.Now;
        for (uint i = (uint)id;
  i < CollisionChecks;
         i += (uint)Environment.ProcessorCount)
        {
          numbers[i] = Encrypt(i);

          child.Tick();
          progress.Tick();


          // if (id == 0)
          //   Debug.WriteLine(i);
        }
      }
    }

    private BigInteger Encrypt(uint plainText)
    {
      return BigInteger.Pow((plainText + Salt1) * Salt2, (int)PublicKey) % N;
    }

    private BigInteger Decrypt(BigInteger cypher)
    {
      return BigInteger.Pow(cypher, (int)PrivateKey) % N / Salt2 - Salt1;
    }

    private ulong Pow(uint i, uint exp)
    {
      if (exp == 1)
        return i;
      else
        return i * Pow(i, exp - 1);
    }

    private ulong Pow(ulong i, uint exp)
    {
      if (exp == 1)
        return i;
      else
        return i * Pow(i, exp - 1);
    }
  }
}
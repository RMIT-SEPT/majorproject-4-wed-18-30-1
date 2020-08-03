using System;

namespace RSA_Evaluation
{
  class Program
  {
    static void Main(string[] args)
    {
      var prog = new Evaluation(uint.Parse(args[0]), uint.Parse(args[1]), uint.Parse(args[2]), uint.Parse(args[3]), uint.Parse(args[4]));

      Console.WriteLine("Tests passed: " + prog.Evaluate());

    }
  }
}

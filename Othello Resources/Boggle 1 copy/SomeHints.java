public class SomeHints {
   public static void main(String [] args) {
   
//       StringBuilder sb = new StringBuilder();
//       StringBuilder sb2 = new StringBuilder(10);
//       StringBuilder sb3 = new StringBuilder("one");
//       
//       sb.append("hello");
//       sb.append('c');
//       sb.append(123);
//       
//       System.out.println(sb);
//       String s = sb.toString();

      String str = "happy";
      char [] letters;
      letters = str.toCharArray();
      
      String back = new String(letters);
      
      
   }
}


1 public class SomeHints
 2 {
 3    public static void main(String [] args)
 4    {
 5       StringBuilder sb = new StringBuilder(); // <- default length = 16
 6       StringBuilder sb2 = new StringBuilder(10); // <- set length to 10
 7       StringBuilder sb3 = new StringBuilder("one");
 8       
 9       sb.append("Hello"); // <- strings,
10       sb.append('c'); // <- characters,
11       sb.append(123); // <- numbers, this guy's super overloaded
12       
13       
14       System.out.println(sb);
15       String s = sb.toString();
16       sb.insert(2,"x");
17       System.out.println(sb);
18       // SUPPORTS characterAt, indexOf, and more!!
19       
20       String str = "hello"; //let's make this a char array
21       char [] letters;
22       letters = str.toCharArray(); //what a good method this is
23       
24       String back = new String(letters); // and look how easy to go back it is!
25       
26    }
27 }
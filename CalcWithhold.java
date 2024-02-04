import java.util.Scanner;
import java.text.NumberFormat;

public class CalcWithhold{
    static Long GATES_WORTH = Long.parseLong("124000000000");
    // ^^^^^ this line is weird because my linter was giving me dumb warnings
    //       when I tried to assign it just the number 124000000000 ¯\_(ツ)_/¯
    static double weeks_in_a_year = 52.1775;

    public static void print(String input){System.out.print(input);}

    public static void println(String input){System.out.println(input);}

    public static String[] run_calc(Double percent, Long actual_inc, NumberFormat fm) {
        double withheld = actual_inc * (percent / 100);
        double num_years = GATES_WORTH / ((actual_inc - withheld) * weeks_in_a_year);
        return new String[]{fm.format(withheld), fm.format(num_years)};
    }

    public static void main(String[] args) throws InterruptedException {
        NumberFormat trunc = NumberFormat.getInstance();
        trunc.setMaximumFractionDigits(2);
        Scanner scan_obj = new Scanner(System.in);
        Long user_input;
        String input_holder;
        String[] app_msgs = {
            "Tax_Withholding_Calculator has launched", // welcome msg
            "Input your typical weekly income before taxes: ", // user prompt
            "for a total withheld amount of about: $", // calc'd dollars msg
            "That's okay! You're average tax rate is ~10%, ", // if income <500
            "Not bad! You're average tax rate is ~15%, ", // if inc btwn 500-1500
            "Make sure to put some of that aside for medical costs!", // if inc btwn 1500-4000 
            "You're average tax rate is ~20%, ", // annual income is around 100k
            "You're average tax rate is ~30%, ", // if inc between 2500-4000
            "To amass the same amount of wealth as Bill gates,\n",
            "you would need to work your current job for: ",
            " years,\nwithout accounting for any living costs...",
            "\nGo gettem, tiger!"
        };

        println(app_msgs[0]);
        print(app_msgs[1]);
        do {
            input_holder = scan_obj.next();
            try {
                user_input = Long.parseLong(input_holder);
                break;
            } catch (NumberFormatException e) {
                if (input_holder == "q") {System.exit(0);}
                println("Something failed when converting your input to a number...");
                print("Try again or enter 'q' to quit: ");
            }
        } while (true);
        scan_obj.close();
        println(""); // making the following prints start on a new line
        // outputting different things based on input
        if (user_input < 500){
            String[] results = run_calc((double) 10, user_input, trunc);
            print(app_msgs[3] + app_msgs[2]);
            print(results[0] + "\n");
            println(app_msgs[8] + app_msgs[9] + results[1] + app_msgs[10]);
            Thread.sleep(2000);
            println(app_msgs[11]);
        } else if (user_input < 1500){
            String[] results = run_calc((double) 15, user_input, trunc);
            print(app_msgs[4] + app_msgs[2]);
            print(results[0] + "\n");
            println(app_msgs[8] + app_msgs[9] + results[1] + app_msgs[10]);
            Thread.sleep(2000);
            println(app_msgs[11]);
        } else if (user_input < 2500){
            String[] results = run_calc((double) 20, user_input, trunc);
            println(app_msgs[5]);
            print(app_msgs[6] + app_msgs[2]);
            print(results[0] + "\n");
            println(app_msgs[8] + app_msgs[9] + results[1] + app_msgs[10]);
            Thread.sleep(2000);
            println(app_msgs[11]);
        } else if (user_input < 4000){
            String[] results = run_calc((double) 30, user_input, trunc);
            print(app_msgs[7] + app_msgs[2]);
            print(results[0] + "\n");
            println(app_msgs[8] + app_msgs[9] + results[1] + app_msgs[10]);
            Thread.sleep(2000);
            println(app_msgs[11]);
        } else {
            String[] results = run_calc((double) 30, user_input, trunc);
            println(app_msgs[7] + app_msgs[2]);
            print(results[0] + "\n");
        }
    }
}

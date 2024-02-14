import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

public class Frame extends JFrame implements ActionListener {
    private final JLabel label = new JLabel("", SwingConstants.CENTER);
    private final JButton start = new JButton("Start");
    private final JButton clear = new JButton("Clear");

    private int seconds;
    private int minutes;
    private int hours;

    private String sec = String.format("%02d", seconds);
    private String min = String.format("%02d", minutes);
    private String hr = String.format("%02d", hours);

    private String time = hr + ":" + min + ":" + sec;
    private int totalTime = 60;

    private final int delay = 1000;
    private boolean isRunnng = false;
    private Timer timer = new Timer(delay, new Listener());

    private void updateStrings() {
        sec = String.format("%02d", this.seconds);
        min = String.format("%02d", this.minutes);
        hr = String.format("%02d", this.hours);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            totalTime += delay;
            hours = totalTime / 3600000;
            minutes = (totalTime / 60000) % 60;
            seconds = (totalTime / 1000) % 60;
            updateStrings();
            time = hr + ":" + min + ":" + sec;
            label.setText(time);
        }
    }

    private void start() {
        timer.start();
        start.setText("Stop");
    }

    private void stop() {
        timer.stop();
        start.setText("Start");
    }

    private void clear() {
        timer.stop();
        isRunnng = false;
        totalTime = 0;
        label.setText("00 : 00: 00");
    }

    public void actionPerformed(ActionEvent e) {
        if (!isRunnng) {
            isRunnng = true;
            if (e.getSource() == start) {
                start();
            }
        }else {
            isRunnng = false;
            stop();
        }

        if(e.getSource() == clear){
            clear();
        }
    }

    Frame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setTitle("Stop Watch");
        this.setResizable(false);
        this.setLayout(null);

        label.setText(time);
        label.setBounds(110, 100, 170, 50);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        this.add(label);

        start.setBounds(70, 170, 100, 50);
        start.setFont(new Font("Arial", Font.BOLD, 18));
        start.addActionListener(this);
        this.add(start);

        clear.setBounds(220, 170, 100, 50);
        clear.setFont(new Font("Arial", Font.BOLD, 18));
        clear.addActionListener(this);
        this.add(clear);

        this.setVisible(true);
    }
}

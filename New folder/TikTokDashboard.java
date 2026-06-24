import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TikTokDashboard {
    public static void main(String[] args) {
        // 1. Create the primary window frame
        JFrame frame = new JFrame("TikTok Creator & Commerce Suite");
        frame.setSize(500, 320); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 

        // 2. Create a layout panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); 

        // 3. Header Title Label
        JLabel titleLabel = new JLabel("TikTok Main Control Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);

        // 4. Create Interactive Buttons
        JButton creatorButton = new JButton("Open Content Creator Analytics");
        JButton uploadButton = new JButton("Upload New Video / Image Draft");
        JButton merchantButton = new JButton("Open TikTok Shop Console");
        JButton exitButton = new JButton("Exit Platform");

        panel.add(creatorButton);
        panel.add(uploadButton); 
        panel.add(merchantButton);
        panel.add(exitButton);

        // ==================== LOGIC AND EVENT HANDLING ====================

        // Logic for Creator Analytics Button
        creatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double views = 500000;
                double likes = 75000;
                double shares = 15000;
                double engagementRate = ((likes + shares) / views) * 100;

                String report = String.format(
                    "--- @creator.hub Analytics ---\n\n" +
                    "Total Views: %,.0f\n" +
                    "Total Likes: %,.0f\n" +
                    "Total Shares: %,.0f\n" +
                    "Calculated Engagement Rate: %.2f%%", 
                    views, likes, shares, engagementRate
                );
                JOptionPane.showMessageDialog(frame, report, "Creator Metrics Window", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Logic for Upload & Preview Window
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select an Image or Video to Upload");

                // Set file filters
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "TikTok Media Files (.mp4, .mov, .jpg, .png)", "mp4", "mov", "jpg", "jpeg", "png"
                );
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName().toLowerCase();
                    String filePath = selectedFile.getAbsolutePath();

                    // Check if the uploaded file is an image
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                        
                        // Create a separate pop-up Window Dialog for the image preview
                        JDialog previewDialog = new JDialog(frame, "TikTok Draft Visual Preview", true);
                        previewDialog.setSize(400, 400);
                        previewDialog.setLocationRelativeTo(frame);

                        // Load and scale the image so it fits nicely inside our popup window
                        ImageIcon rawIcon = new ImageIcon(filePath);
                        Image scaledImage = rawIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
                        ImageIcon finalIcon = new ImageIcon(scaledImage);

                        // Create a label container to hold the image asset
                        JLabel imageLabel = new JLabel(finalIcon, JLabel.CENTER);
                        
                        // Wrap inside a Scroll Pane in case the user uploads something massive
                        JScrollPane scrollPane = new JScrollPane(imageLabel);
                        
                        previewDialog.add(scrollPane);
                        
                        // Inform the user via text first, then pop open the image draft window
                        JOptionPane.showMessageDialog(frame, "Image uploaded successfully! Displaying preview window...");
                        previewDialog.setVisible(true);

                    } else {
                        // If it's a video file (.mp4, .mov), display text details (since playing actual video requires external libraries)
                        String confirmationMsg = String.format(
                            "🎉 Video Upload Successful! 🎉\n\n" +
                            "File Name: %s\n" +
                            "Your video draft has been cached for editing.", fileName
                        );
                        JOptionPane.showMessageDialog(frame, confirmationMsg, "Upload Engine Status", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Upload operation cancelled.", "System Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Logic for Merchant Button Click
        merchantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double grossSales = 1500.00;
                double platformFee = grossSales * 0.05; 
                double netPayout = grossSales - platformFee;

                String report = String.format(
                    "--- TikTok Shop Manager ---\n\n" +
                    "Gross Revenue: $%.2f\n" +
                    "TikTok Commission (5%%): $%.2f\n" +
                    "Net Payout to Bank: $%.2f", 
                    grossSales, platformFee, netPayout
                );
                JOptionPane.showMessageDialog(frame, report, "Merchant Financial Window", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Logic for Exit Button Click
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
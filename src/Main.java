import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Main extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton togglePasswordButton;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private int attemptCount = 0;
    private static final int MAX_ATTEMPTS = 3;
    private boolean passwordVisible = false;

    public Main() {
        initializeUI();
    }

    private void initializeUI() {
        // Frame setup
        setTitle("Car Rental System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth(), h = getHeight();

                Color color1 = new Color(15, 32, 39);
                Color color2 = new Color(32, 58, 67);
                Color color3 = new Color(44, 83, 100);

                GradientPaint gp1 = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp1);
                g2d.fillRect(0, 0, w, h);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
                g2d.setColor(new Color(0, 168, 255));
                g2d.fillOval(-100, -100, 400, 400);
                g2d.fillOval(w - 300, h - 300, 400, 400);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            }
        };
        mainPanel.setLayout(new GridBagLayout());

        // Left side - Branding panel
        JPanel brandingPanel = createBrandingPanel();

        // Right side - Login panel
        JPanel loginPanel = createLoginPanel();

        // Container for both panels
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 20);
        contentPanel.add(brandingPanel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        contentPanel.add(loginPanel, gbc);

        mainPanel.add(contentPanel);
        add(mainPanel);
    }

    private JPanel createBrandingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(400, 550));


        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Brand title
        JLabel brandTitle = new JLabel("Car rentals");
        brandTitle.setFont(new Font("Segoe UI", Font.BOLD, 48));
        brandTitle.setForeground(Color.WHITE);
        brandTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(brandTitle);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));



        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Glassmorphism effect
                g2d.setColor(new Color(255, 255, 255, 25));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Border
                g2d.setColor(new Color(255, 255, 255, 60));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(450, 550));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Login header
        JLabel loginHeader = new JLabel("Welcome Back");
        loginHeader.setFont(new Font("Segoe UI", Font.BOLD, 32));
        loginHeader.setForeground(Color.WHITE);
        loginHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginHeader);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel loginSubheader = new JLabel("Sign in to access your dashboard");
        loginSubheader.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginSubheader.setForeground(new Color(200, 220, 255));
        loginSubheader.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginSubheader);

        panel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Username section
        JLabel usernameIcon = new JLabel("👤");
        usernameIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        usernameIcon.setForeground(Color.WHITE);
        usernameIcon.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel usernameLabel = new JLabel("  Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(200, 220, 255));

        JPanel usernameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        usernameLabelPanel.setOpaque(false);
        usernameLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabelPanel.add(usernameIcon);
        usernameLabelPanel.add(usernameLabel);
        panel.add(usernameLabelPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        usernameField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 30));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setOpaque(false);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15, new Color(255, 255, 255, 80)),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        panel.add(usernameField);

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Password section
        JLabel passwordIcon = new JLabel("🔒");
        passwordIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        passwordIcon.setForeground(Color.WHITE);

        JLabel passwordLabel = new JLabel("  Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(200, 220, 255));

        JPanel passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        passwordLabelPanel.setOpaque(false);
        passwordLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabelPanel.add(passwordIcon);
        passwordLabelPanel.add(passwordLabel);
        panel.add(passwordLabelPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Password field with toggle
        JPanel passwordPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 30));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        passwordPanel.setOpaque(false);
        passwordPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        passwordPanel.setBorder(new RoundedBorder(15, new Color(255, 255, 255, 80)));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setOpaque(false);
        passwordField.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 8));
        passwordField.setEchoChar('*');
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        passwordPanel.add(passwordField, BorderLayout.CENTER);

        togglePasswordButton = new JButton("Show");
        togglePasswordButton.setFont(new Font("Segoe UI Emoji", 0, 18));
        togglePasswordButton.setForeground(new Color(200, 220, 255));
        togglePasswordButton.setPreferredSize(new Dimension(50, 50));
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setContentAreaFilled(false);
        togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        togglePasswordButton.addActionListener(e -> togglePasswordVisibility());
        passwordPanel.add(togglePasswordButton, BorderLayout.EAST);

        panel.add(passwordPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Attempts label
        attemptsLabel = new JLabel("Attempts remaining: 3");
        attemptsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        attemptsLabel.setForeground(new Color(150, 200, 255));
        attemptsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(attemptsLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Login button
        loginButton = new JButton("Sign In") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2d.setColor(new Color(0, 120, 215));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(0, 150, 255));
                } else {
                    g2d.setColor(new Color(0, 168, 255));
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> performLogin());
        panel.add(loginButton);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Message label
        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(messageLabel);

        // Enter key support
        passwordField.addActionListener(e -> performLogin());

        return panel;
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            passwordField.setEchoChar((char) 0);
            togglePasswordButton.setText("Hide");
        } else {
            passwordField.setEchoChar('*');
            togglePasswordButton.setText("Show");
        }
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println("Login Attempt " + (attemptCount + 1) + ":");
        System.out.println("Username entered: " + username);
        System.out.println("Password entered: " + "*".repeat(password.length()));
        System.out.println("---");

        if (LoginCredentials.validateCredentials(username, password)) {
            messageLabel.setForeground(new Color(100, 255, 150));
            messageLabel.setText("✓ Login Successful!");
            System.out.println("✓ Login Successful!");
            System.out.println("Welcome, " + username + "!");

            JOptionPane.showMessageDialog(this,
                    "Login Successful!\nWelcome to RentWheels, " + username + "!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        } else {
            attemptCount++;
            int remainingAttempts = MAX_ATTEMPTS - attemptCount;

            if (remainingAttempts > 0) {
                messageLabel.setForeground(new Color(255, 120, 120));
                messageLabel.setText("✗ Invalid credentials. Try again.");
                attemptsLabel.setText("Attempts remaining: " + remainingAttempts);
                attemptsLabel.setForeground(remainingAttempts == 1 ? new Color(255, 100, 100) : new Color(150, 200, 255));
                System.out.println("✗ Login Failed! Incorrect username or password.");
                System.out.println("Remaining attempts: " + remainingAttempts);
                System.out.println("---");

                passwordField.setText("");

                // Shake animation
                shakeComponent(usernameField);
                shakeComponent(passwordField);
            } else {
                messageLabel.setForeground(new Color(255, 80, 80));
                messageLabel.setText("✗ Account locked. Too many attempts.");
                attemptsLabel.setText("Attempts remaining: 0");
                attemptsLabel.setForeground(new Color(255, 80, 80));
                System.out.println("✗ Maximum login attempts exceeded!");
                System.out.println("System locked for security.");

                usernameField.setEnabled(false);
                passwordField.setEnabled(false);
                loginButton.setEnabled(false);
                togglePasswordButton.setEnabled(false);

                JOptionPane.showMessageDialog(this,
                        "Maximum login attempts exceeded!\nThe system has been locked for security.",
                        "Access Denied",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void shakeComponent(JComponent component) {
        Point originalLocation = component.getLocation();
        Timer timer = new Timer(50, null);
        final int[] count = {0};

        timer.addActionListener(e -> {
            if (count[0] < 6) {
                int offset = (count[0] % 2 == 0) ? 10 : -10;
                component.setLocation(originalLocation.x + offset, originalLocation.y);
                count[0]++;
            } else {
                component.setLocation(originalLocation);
                timer.stop();
            }
        });
        timer.start();
    }

    static class RoundedBorder extends AbstractBorder {
        private int radius;
        private Color color;

        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2, 2, 2);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=================================");
        System.out.println("RENTWHEELS - CAR RENTAL SYSTEM");
        System.out.println("=================================");
        System.out.println("Valid Credentials:");
        System.out.println("Username: " + LoginCredentials.VALID_USERNAME);
        System.out.println("Password: " + LoginCredentials.VALID_PASSWORD);
        System.out.println("=================================");
        System.out.println("You have 3 attempts to login.");
        System.out.println("=================================\n");

        SwingUtilities.invokeLater(() -> {
            Main loginFrame = new Main();
            loginFrame.setVisible(true);
        });
    }
}
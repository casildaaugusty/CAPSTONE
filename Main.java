import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField loginUsername;
    private JPasswordField loginPassword;

    private JTextField registerUsername;
    private JPasswordField registerPassword;
    private JPasswordField confirmPassword;

    private String savedUsername = "";
    private String savedPassword = "";

    public Main() {

        setTitle("CasildaMart");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPage(), "LOGIN");
        mainPanel.add(createRegisterPage(), "REGISTER");
        mainPanel.add(createHomePage(), "HOME");

        add(mainPanel);

        cardLayout.show(mainPanel, "LOGIN");
    }

    // ================= LOGIN PAGE =================

    private JPanel createLoginPage() {

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("CasildaMart");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(155, 30, 220, 40);
        panel.add(title);

        JLabel subtitle = new JLabel("Login to your account");
        subtitle.setBounds(175, 75, 180, 25);
        panel.add(subtitle);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 125, 100, 25);
        panel.add(usernameLabel);

        loginUsername = new JTextField();
        loginUsername.setBounds(180, 120, 220, 35);
        panel.add(loginUsername);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 175, 100, 25);
        panel.add(passwordLabel);

        loginPassword = new JPasswordField();
        loginPassword.setBounds(180, 170, 220, 35);
        panel.add(loginPassword);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(180, 225, 100, 35);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(290, 225, 110, 35);
        panel.add(registerButton);

        loginButton.addActionListener(e -> login());

        registerButton.addActionListener(e -> {
            clearRegisterFields();
            cardLayout.show(mainPanel, "REGISTER");
        });

        return panel;
    }

    // ================= REGISTER PAGE =================

    private JPanel createRegisterPage() {

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(145, 25, 230, 40);
        panel.add(title);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 90, 120, 25);
        panel.add(usernameLabel);

        registerUsername = new JTextField();
        registerUsername.setBounds(190, 85, 220, 35);
        panel.add(registerUsername);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 140, 120, 25);
        panel.add(passwordLabel);

        registerPassword = new JPasswordField();
        registerPassword.setBounds(190, 135, 220, 35);
        panel.add(registerPassword);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(60, 190, 120, 25);
        panel.add(confirmLabel);

        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(190, 185, 220, 35);
        panel.add(confirmPassword);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(190, 240, 100, 35);
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 240, 100, 35);
        panel.add(backButton);

        registerButton.addActionListener(e -> register());

        backButton.addActionListener(e ->
                cardLayout.show(mainPanel, "LOGIN")
        );

        return panel;
    }

    // ================= HOME PAGE =================

    private JPanel createHomePage() {

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("Welcome to CasildaMart");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(105, 40, 350, 40);
        panel.add(title);

        JLabel subtitle = new JLabel("Online Shopping Application");
        subtitle.setBounds(170, 85, 220, 25);
        panel.add(subtitle);

        JButton productsButton = new JButton("Products");
        productsButton.setBounds(90, 145, 140, 45);
        panel.add(productsButton);

        JButton cartButton = new JButton("My Cart");
        cartButton.setBounds(270, 145, 140, 45);
        panel.add(cartButton);

        JButton ordersButton = new JButton("My Orders");
        ordersButton.setBounds(90, 210, 140, 45);
        panel.add(ordersButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(270, 210, 140, 45);
        panel.add(logoutButton);

        productsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Products section"
                )
        );

        cartButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Your cart is empty."
                )
        );

        ordersButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "No orders available."
                )
        );

        logoutButton.addActionListener(e -> {
            loginUsername.setText("");
            loginPassword.setText("");
            cardLayout.show(mainPanel, "LOGIN");
        });

        return panel;
    }

    // ================= LOGIN FUNCTION =================

    private void login() {

        String username = loginUsername.getText().trim();
        String password = new String(loginPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (username.equals(savedUsername)
                && password.equals(savedPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            cardLayout.show(mainPanel, "HOME");

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= REGISTER FUNCTION =================

    private void register() {

        String username = registerUsername.getText().trim();
        String password = new String(registerPassword.getPassword());
        String confirm = new String(confirmPassword.getPassword());

        if (username.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!password.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        savedUsername = username;
        savedPassword = password;

        JOptionPane.showMessageDialog(
                this,
                "Registration successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        loginUsername.setText(username);
        loginPassword.setText("");

        cardLayout.show(mainPanel, "LOGIN");
    }

    private void clearRegisterFields() {
        registerUsername.setText("");
        registerPassword.setText("");
        confirmPassword.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
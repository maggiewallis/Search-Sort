
package searchsort;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class SearchSortFrame extends javax.swing.JFrame implements ThreadCompletedDelegate,Constants
{

    public SearchSortFrame() {
        initComponents();
        barPanel.setBackground(Color.white);
        mainArray = new BarArray();
        displayManager = new DisplayThread(mainArray,barPanel,barInfoLabel);

        statsThread = new StatsThread(numGetsLabel,
                                      numSetsLabel,
                                      numComparesLabel,
                                      timeLabel);
        AbstractSearchSortThread.setAutoReset(AutoResetCheckBox.isSelected());
    }

    public void startThreads()
    {
        displayManager.start();
        statsThread.start();

    }
    
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        upperPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        OrderCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        OperationCombo = new javax.swing.JComboBox();
        InverseCheckbox = new javax.swing.JCheckBox();
        startButton = new javax.swing.JButton();
        pauseResumeButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        StatsPanel = new javax.swing.JPanel();
        NumGetsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        numGetsLabel = new javax.swing.JLabel();
        NumSetsPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        numSetsLabel = new javax.swing.JLabel();
        NumComparesPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        numComparesLabel = new javax.swing.JLabel();
        TimePanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        statusPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        statusField = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ResetPanel = new javax.swing.JPanel();
        ManualResetBotton = new javax.swing.JButton();
        AutoResetCheckBox = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        DelayPanel = new javax.swing.JPanel();
        GetDelayPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        getDelaySlider = new javax.swing.JSlider();
        getDelayNumber = new javax.swing.JLabel();
        getDelayMultiplier = new javax.swing.JCheckBox();
        SetDelayPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        setDelaySlider = new javax.swing.JSlider();
        setDelayNumber = new javax.swing.JLabel();
        setDelayMultiplier = new javax.swing.JCheckBox();
        CompareDelayPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        compareDelaySlider = new javax.swing.JSlider();
        compareDelayNumber = new javax.swing.JLabel();
        compareDelayMultiplier = new javax.swing.JCheckBox();
        lowerPanel = new javax.swing.JPanel();
        barInfoLabel = new javax.swing.JLabel();
        barPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        revertMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        upperPanel.setLayout(new javax.swing.BoxLayout(upperPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jLabel1.setText("Sort by:");
        jPanel1.add(jLabel1);

        OrderCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Top", "Bottom", "Size", "Red", "Green", "Blue", "Brightness" }));
        OrderCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderComboActionPerformed(evt);
            }
        });
        jPanel1.add(OrderCombo);

        jLabel2.setText("Operation:");
        jPanel1.add(jLabel2);

        OperationCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*Linear Search", "*Binary Search (iterative)", "*Binary Search (recursive)", "-", "Randomize", "Bubble Sort", "Bozo Sort", "*Selection Sort", "*Insertion Sort", "*Merge Sort", "Quick Sort", "Shell Sort", "Heap Sort" }));
        OperationCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OperationComboActionPerformed(evt);
            }
        });
        jPanel1.add(OperationCombo);

        InverseCheckbox.setText("Inverse Order");
        InverseCheckbox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        InverseCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InverseCheckboxActionPerformed(evt);
            }
        });
        jPanel1.add(InverseCheckbox);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonPressed(evt);
            }
        });
        jPanel1.add(startButton);

        pauseResumeButton.setText("Pause");
        pauseResumeButton.setEnabled(false);
        pauseResumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handlePauseResumeButton(evt);
            }
        });
        jPanel1.add(pauseResumeButton);

        stepButton.setText("Step");
        stepButton.setEnabled(false);
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleStepButton(evt);
            }
        });
        jPanel1.add(stepButton);

        upperPanel.add(jPanel1);
        upperPanel.add(jSeparator3);

        jPanel2.setMaximumSize(new java.awt.Dimension(1000, 131068));
        jPanel2.setMinimumSize(new java.awt.Dimension(1000, 87));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 152));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

        StatsPanel.setMaximumSize(new java.awt.Dimension(300, 131068));
        StatsPanel.setMinimumSize(new java.awt.Dimension(300, 64));
        StatsPanel.setPreferredSize(new java.awt.Dimension(200, 100));
        StatsPanel.setLayout(new javax.swing.BoxLayout(StatsPanel, javax.swing.BoxLayout.Y_AXIS));

        NumGetsPanel.setLayout(new java.awt.GridLayout(1, 2));

        jLabel3.setText("Gets");
        jLabel3.setMaximumSize(new java.awt.Dimension(50, 16));
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 16));
        jLabel3.setPreferredSize(new java.awt.Dimension(50, 16));
        jLabel3.setSize(new java.awt.Dimension(50, 16));
        NumGetsPanel.add(jLabel3);

        numGetsLabel.setText("0");
        numGetsLabel.setMaximumSize(new java.awt.Dimension(50, 16));
        numGetsLabel.setMinimumSize(new java.awt.Dimension(50, 16));
        numGetsLabel.setPreferredSize(new java.awt.Dimension(50, 16));
        numGetsLabel.setSize(new java.awt.Dimension(50, 16));
        NumGetsPanel.add(numGetsLabel);

        StatsPanel.add(NumGetsPanel);

        NumSetsPanel.setLayout(new java.awt.GridLayout(1, 2));

        jLabel4.setText("Sets");
        jLabel4.setMaximumSize(new java.awt.Dimension(50, 16));
        jLabel4.setMinimumSize(new java.awt.Dimension(50, 16));
        jLabel4.setPreferredSize(new java.awt.Dimension(50, 16));
        jLabel4.setSize(new java.awt.Dimension(50, 16));
        NumSetsPanel.add(jLabel4);

        numSetsLabel.setText("0");
        numSetsLabel.setMaximumSize(new java.awt.Dimension(50, 16));
        numSetsLabel.setMinimumSize(new java.awt.Dimension(50, 16));
        numSetsLabel.setPreferredSize(new java.awt.Dimension(50, 16));
        numSetsLabel.setSize(new java.awt.Dimension(50, 16));
        NumSetsPanel.add(numSetsLabel);

        StatsPanel.add(NumSetsPanel);

        NumComparesPanel.setLayout(new java.awt.GridLayout(1, 2));

        jLabel5.setText("Compares");
        jLabel5.setMaximumSize(new java.awt.Dimension(50, 16));
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 16));
        jLabel5.setPreferredSize(new java.awt.Dimension(50, 16));
        jLabel5.setSize(new java.awt.Dimension(50, 16));
        NumComparesPanel.add(jLabel5);

        numComparesLabel.setText("0");
        numComparesLabel.setMaximumSize(new java.awt.Dimension(50, 16));
        numComparesLabel.setMinimumSize(new java.awt.Dimension(50, 16));
        numComparesLabel.setPreferredSize(new java.awt.Dimension(50, 16));
        numComparesLabel.setSize(new java.awt.Dimension(50, 16));
        NumComparesPanel.add(numComparesLabel);

        StatsPanel.add(NumComparesPanel);

        TimePanel.setLayout(new java.awt.GridLayout(1, 2));

        jLabel9.setText("Time");
        jLabel9.setMaximumSize(new java.awt.Dimension(50, 16));
        jLabel9.setMinimumSize(new java.awt.Dimension(50, 16));
        jLabel9.setPreferredSize(new java.awt.Dimension(50, 16));
        jLabel9.setSize(new java.awt.Dimension(50, 16));
        TimePanel.add(jLabel9);

        timeLabel.setText("0:00:00");
        timeLabel.setPreferredSize(new java.awt.Dimension(50, 16));
        timeLabel.setSize(new java.awt.Dimension(50, 16));
        TimePanel.add(timeLabel);

        StatsPanel.add(TimePanel);

        statusPanel.setLayout(new java.awt.GridLayout(1, 0));

        jLabel6.setText("Status");
        statusPanel.add(jLabel6);

        statusField.setText("Unsorted");
        statusPanel.add(statusField);

        StatsPanel.add(statusPanel);

        jPanel2.add(StatsPanel);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setMaximumSize(new java.awt.Dimension(12, 32767));
        jSeparator1.setMinimumSize(new java.awt.Dimension(12, 0));
        jPanel2.add(jSeparator1);

        ResetPanel.setLayout(new javax.swing.BoxLayout(ResetPanel, javax.swing.BoxLayout.Y_AXIS));

        ManualResetBotton.setText("Reset");
        ManualResetBotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManualResetBottonActionPerformed(evt);
            }
        });
        ResetPanel.add(ManualResetBotton);

        AutoResetCheckBox.setSelected(true);
        AutoResetCheckBox.setText("Auto Reset");
        AutoResetCheckBox.setMaximumSize(new java.awt.Dimension(105, 23));
        AutoResetCheckBox.setMinimumSize(new java.awt.Dimension(105, 23));
        AutoResetCheckBox.setPreferredSize(new java.awt.Dimension(105, 23));
        AutoResetCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutoResetCheckBoxActionPerformed(evt);
            }
        });
        ResetPanel.add(AutoResetCheckBox);

        jPanel2.add(ResetPanel);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator2);

        DelayPanel.setMaximumSize(new java.awt.Dimension(600, 87));
        DelayPanel.setMinimumSize(new java.awt.Dimension(600, 87));
        DelayPanel.setPreferredSize(new java.awt.Dimension(600, 100));
        DelayPanel.setLayout(new javax.swing.BoxLayout(DelayPanel, javax.swing.BoxLayout.Y_AXIS));

        GetDelayPanel.setLayout(new javax.swing.BoxLayout(GetDelayPanel, javax.swing.BoxLayout.X_AXIS));

        jLabel7.setText("\"Get\" Delay");
        jLabel7.setMaximumSize(new java.awt.Dimension(105, 16));
        jLabel7.setMinimumSize(new java.awt.Dimension(105, 16));
        jLabel7.setPreferredSize(new java.awt.Dimension(105, 16));
        GetDelayPanel.add(jLabel7);

        getDelaySlider.setMaximum(99);
        getDelaySlider.setValue(0);
        getDelaySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateGetDelay(evt);
            }
        });
        GetDelayPanel.add(getDelaySlider);

        getDelayNumber.setText("00");
        GetDelayPanel.add(getDelayNumber);

        getDelayMultiplier.setText("x100");
        getDelayMultiplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGetCheckbox(evt);
            }
        });
        GetDelayPanel.add(getDelayMultiplier);

        DelayPanel.add(GetDelayPanel);

        SetDelayPanel.setLayout(new javax.swing.BoxLayout(SetDelayPanel, javax.swing.BoxLayout.X_AXIS));

        jLabel8.setText("\"Set\" Delay");
        jLabel8.setMaximumSize(new java.awt.Dimension(105, 16));
        jLabel8.setMinimumSize(new java.awt.Dimension(105, 16));
        jLabel8.setPreferredSize(new java.awt.Dimension(105, 16));
        SetDelayPanel.add(jLabel8);

        setDelaySlider.setMaximum(99);
        setDelaySlider.setValue(0);
        setDelaySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateSetDelay(evt);
            }
        });
        SetDelayPanel.add(setDelaySlider);

        setDelayNumber.setText("00");
        SetDelayPanel.add(setDelayNumber);

        setDelayMultiplier.setText("x100");
        setDelayMultiplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSetCheckbox(evt);
            }
        });
        SetDelayPanel.add(setDelayMultiplier);

        DelayPanel.add(SetDelayPanel);

        CompareDelayPanel.setLayout(new javax.swing.BoxLayout(CompareDelayPanel, javax.swing.BoxLayout.X_AXIS));

        jLabel11.setText("\"Compare\" Delay");
        CompareDelayPanel.add(jLabel11);

        compareDelaySlider.setMaximum(99);
        compareDelaySlider.setValue(0);
        compareDelaySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateCompareDelay(evt);
            }
        });
        CompareDelayPanel.add(compareDelaySlider);

        compareDelayNumber.setText("00");
        CompareDelayPanel.add(compareDelayNumber);

        compareDelayMultiplier.setText("x100");
        compareDelayMultiplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCompareCheckbox(evt);
            }
        });
        CompareDelayPanel.add(compareDelayMultiplier);

        DelayPanel.add(CompareDelayPanel);

        jPanel2.add(DelayPanel);

        upperPanel.add(jPanel2);

        jSplitPane1.setLeftComponent(upperPanel);

        lowerPanel.setLayout(new javax.swing.BoxLayout(lowerPanel, javax.swing.BoxLayout.Y_AXIS));

        barInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barInfoLabel.setText("Mouse over a Bar for stats");
        barInfoLabel.setMaximumSize(new java.awt.Dimension(9999, 16));
        barInfoLabel.setPreferredSize(new java.awt.Dimension(0, 16));
        lowerPanel.add(barInfoLabel);

        barPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                handleMouseExited(evt);
            }
        });
        barPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                handleMouseMoved(evt);
            }
        });
       
        barPanel.setLayout(new GridLayout(1,1));
        lowerPanel.add(barPanel);

        jSplitPane1.setBottomComponent(lowerPanel);

        fileMenu.setText("File");

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.META_MASK));
        newMenuItem.setText("New Bar Array");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleNewMenuItem(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
        openMenuItem.setText("Open Bar Array...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleOpenMenuItem(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
        saveMenuItem.setText("Save Bar Array");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleSaveMenuItem(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        saveAsMenuItem.setText("Save Bar Array As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleSaveAsMenuItem(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        revertMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.META_MASK));
        revertMenuItem.setText("Revert");
        revertMenuItem.setEnabled(false);
        revertMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleRevertMenuItem(evt);
            }
        });
        fileMenu.add(revertMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

       
        getContentPane().setLayout(new GridLayout(1,1));
        getContentPane().add(jSplitPane1);
        pack();
    }

    private void OrderComboActionPerformed(java.awt.event.ActionEvent evt) {
        int whichOrderSelected = OrderCombo.getSelectedIndex();
        if (whichOrderSelected>-1 && whichOrderSelected<BAR_COMPARE_TYPE_STRINGS.length)
            SortableBar.setCurrentComparisonType(whichOrderSelected);
        else 
            SortableBar.setCurrentComparisonType(BAR_COMPARE_BY_TOP);

        
        if (mainArray.isSorted())
            updateStatus(AbstractSearchSortThread.STATUS_SORTED);
        else
            updateStatus(AbstractSearchSortThread.STATUS_UNSORTED);
    }

    private void InverseCheckboxActionPerformed(java.awt.event.ActionEvent evt) {
        SortableBar.setInvertOrder(InverseCheckbox.isSelected());
    }

    private void ManualResetBottonActionPerformed(java.awt.event.ActionEvent evt) {
        statsThread.reset();
    }

    private void AutoResetCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        AbstractSearchSortThread.setAutoReset(AutoResetCheckBox.isSelected());
    }
    
    private void startButtonPressed(java.awt.event.ActionEvent evt) {
        if (startButton.getText().equals("Start"))
        {
            activeThread = null;
            if (OperationCombo.getSelectedItem().equals("Bubble Sort"))
            {  
                activeThread = new BubbleSortThread(mainArray,statsThread,this);
            }
            if (OperationCombo.getSelectedItem().equals("Bozo Sort"))
            {   
                activeThread = new BozoSortThread(mainArray,statsThread,this);
            }
            if (OperationCombo.getSelectedItem().equals("*Selection Sort"))
            {  
                activeThread = new SelectionSortThread(mainArray,statsThread,this);
            }
            if (OperationCombo.getSelectedItem().equals("*Insertion Sort"))
            {   
                activeThread = new InsertionSortThread(mainArray,statsThread,this);
            }
            if (OperationCombo.getSelectedItem().equals("*Merge Sort"))
            {
           		activeThread = new MergeSortThread(mainArray,statsThread,this);
            }
            if (OperationCombo.getSelectedItem().equals("Quick Sort"))
            {
           		activeThread = new MergeSortThread(mainArray,statsThread,this);
            }
            if (OperationCombo.getSelectedItem().equals("*Linear Search"))
            {
                double searchValue = requestSearchValue();
                if (-1 == searchValue)
                    return;
                activeThread = new LinearSearchThread(mainArray,statsThread,this,searchValue);
            }
            if (OperationCombo.getSelectedItem().equals("*Binary Search (recursive)"))
            {
               double searchValue = requestSearchValue();
                if (-1 == searchValue)
                    return;
                activeThread = new BinarySearchThread(mainArray,statsThread,this,searchValue);
            }
            if (null!=activeThread)
            {
            		updateGUIForStart();
                activeThread.start();
            }
        }
        else if (startButton.getText().equals("Stop"))
        {
            activeThread.cancelProcess();
            pauseResumeButton.setText("Pause");
            pauseResumeButton.setEnabled(false);
            stepButton.setEnabled(false);
        }
    }

    private void updateGetDelay(javax.swing.event.ChangeEvent evt) {
        reviseGetDelay();
    }

    private void updateSetDelay(javax.swing.event.ChangeEvent evt) {
        reviseSetDelay();
    }//GEN-LAST:event_updateSetDelay

    private void updateCompareDelay(javax.swing.event.ChangeEvent evt) {
    }

    private void updateGetCheckbox(java.awt.event.ActionEvent evt) {
        reviseGetDelay();
    }

    private void updateSetCheckbox(java.awt.event.ActionEvent evt) {
        reviseSetDelay();
    }
    private void updateCompareCheckbox(java.awt.event.ActionEvent evt) {
        reviseCompareDelay();
    }

    private void handleMouseMoved(java.awt.event.MouseEvent evt) {
        String info = mainArray.getSelectedBarInfo((int)((evt.getX()-10)/SortableBar.getBarWidth()));
        barInfoLabel.setText(info);
    }

    private void handlePauseResumeButton(java.awt.event.ActionEvent evt) {
        if (pauseResumeButton.getText().equals("Pause"))
        {
            activeThread.pauseProcess();
            stepButton.setEnabled(true);
            pauseResumeButton.setText("Resume");
        }
        else
        {
            activeThread.resumeProcess();
            stepButton.setEnabled(false);
            pauseResumeButton.setText("Pause");
        }
    }

    private void handleStepButton(java.awt.event.ActionEvent evt) {
       activeThread.stepProcess();
    }

    private void handleMouseExited(java.awt.event.MouseEvent evt) {
       barInfoLabel.setText("Mouse over a Bar for stats");
    }

    private void handleNewMenuItem(java.awt.event.ActionEvent evt) {
        JTextField field = new JTextField();
        String initialValue = "100";
        Object o = JOptionPane.showInputDialog(this, "How many bars should be included in the new list?", "New Bar List", JOptionPane.QUESTION_MESSAGE,null,null,initialValue);
        String s = (String)o;
        int numBars;
        try
        {
            numBars = Integer.parseInt(s);
        }
        catch (NumberFormatException nfe)
        {
            return;
        }
        if (numBars<2)
            return;
        mainArray = new BarArray(numBars);
        ActionIndicatorQueue.sharedActionIndicatorQueue().clear();
        displayManager.setNewBarArray(mainArray);
        repaint();
        saveMenuItem.setEnabled(true);
        revertMenuItem.setEnabled(false);
    }//GEN-LAST:event_handleNewMenuItem

    private void handleOpenMenuItem(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(theFile);
        chooser.setMultiSelectionEnabled(false);
        int response = chooser.showOpenDialog(this);
        if (JFileChooser.CANCEL_OPTION == response)
            return;
        theFile = chooser.getSelectedFile();
        doOpen();
    }

    private void handleSaveMenuItem(java.awt.event.ActionEvent evt) {
        if (null == theFile)
            handleSaveAsMenuItem(evt);
        else
            doSave();
    }

    private void handleSaveAsMenuItem(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(theFile);
        chooser.setMultiSelectionEnabled(false);
        int response = chooser.showSaveDialog(this);
        if (JFileChooser.CANCEL_OPTION == response)
            return;
        String path = chooser.getSelectedFile().getPath();
        if (!path.contains("."))
            path+= ".bars";
        theFile = new File(path);

        doSave();


    }

    private void handleRevertMenuItem(java.awt.event.ActionEvent evt) {
        doOpen();
    }

    private void OperationComboActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void doSave()
    {
        try
        {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(theFile));
            fileOut.write(mainArray.toString());
            fileOut.close();
            saveMenuItem.setEnabled(false);
            revertMenuItem.setEnabled(false);
        }
        catch (Exception fnfe)
        {
            return;
        }

    }

    
    private void doOpen()
    {
        try
        {
            Scanner reader = new Scanner(theFile);
            int numBars = reader.nextInt();
            reader.nextLine();
            String lines[] = new String[numBars];
            int i=0;
            while(reader.hasNext()&&i<numBars)
            {
                lines[i] = reader.nextLine();
                i++;
            }
            reader.close();
            mainArray = new BarArray(lines);
            ActionIndicatorQueue.sharedActionIndicatorQueue().clear();
            displayManager.setNewBarArray(mainArray);
            repaint();
            saveMenuItem.setEnabled(false);
            revertMenuItem.setEnabled(false);
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("could not read file correctly.");
            return;
        }
    }

    
    public void threadHasFinished(String whichThread, int status)
    {
        System.out.println("Finished. "+whichThread+". status: "+STATUS_STRINGS[status]);
        startButton.setText("Start");
        OrderCombo.setEnabled(true);
        OperationCombo.setEnabled(true);
        InverseCheckbox.setEnabled(true);
        statsThread.stopCheckingStats();
        fileMenu.setEnabled(true);
        updateStatus(status);
        if (AbstractSearchSortThread.STATUS_FINISHED== status)
        {
            saveMenuItem.setEnabled(true);
            revertMenuItem.setEnabled(null!=theFile);
        }
        return;
    }

    
    public void updateStatus(int s)
    {
        statusField.setText(STATUS_STRINGS[s]);
    }

    
    private void updateGUIForStart()
    {
        startButton.setText("Stop");
        OrderCombo.setEnabled(false);
        OperationCombo.setEnabled(false);
        InverseCheckbox.setEnabled(false);
        pauseResumeButton.setText("Pause");
        pauseResumeButton.setEnabled(true);
        fileMenu.setEnabled(false);
        stepButton.setEnabled(false);

    }

    private void reviseGetDelay()
    {
        getDelayNumber.setText(String.format("%02d",getDelaySlider.getValue()));
        if (getDelayMultiplier.isSelected())
            BarArray.updateGetDelay(getDelaySlider.getValue()*100);
        else
            BarArray.updateGetDelay(getDelaySlider.getValue());
    }

    private void reviseSetDelay()
    {
        setDelayNumber.setText(String.format("%02d",setDelaySlider.getValue()));
        if (setDelayMultiplier.isSelected())
            BarArray.updateSetDelay(setDelaySlider.getValue()*100);
        else
            BarArray.updateSetDelay(setDelaySlider.getValue());
    }

    private void reviseCompareDelay()
    {
        compareDelayNumber.setText(String.format("%02d",compareDelaySlider.getValue()));
        if (compareDelayMultiplier.isSelected())
            SortableBar.updateCompareDelay(compareDelaySlider.getValue()*100);
        else
            SortableBar.updateCompareDelay(compareDelaySlider.getValue());
    }

   
    private double requestSearchValue()
    {
        String s = JOptionPane.showInputDialog(this,
                                                        "Search for bar with "+SortableBar.getCompareTypeString()+" = __._%",
                                                        "Search terms",
                                                        JOptionPane.QUESTION_MESSAGE);
        if (null == s)
            return - 1;
        double value;
        try
        {
            value = Double.parseDouble(s);
        }
        catch (NumberFormatException nfe)
        {
            return - 1;
        }
        return value;
    }
   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SearchSortFrame theFrame = new SearchSortFrame();
                theFrame.setSize(1000, 600);
                theFrame.setVisible(true);
                theFrame.startThreads();
            }
        });
    }

    private javax.swing.JCheckBox AutoResetCheckBox;
    private javax.swing.JPanel CompareDelayPanel;
    private javax.swing.JPanel DelayPanel;
    private javax.swing.JPanel GetDelayPanel;
    private javax.swing.JCheckBox InverseCheckbox;
    private javax.swing.JButton ManualResetBotton;
    private javax.swing.JPanel NumComparesPanel;
    private javax.swing.JPanel NumGetsPanel;
    private javax.swing.JPanel NumSetsPanel;
    private javax.swing.JComboBox OperationCombo;
    private javax.swing.JComboBox OrderCombo;
    private javax.swing.JPanel ResetPanel;
    private javax.swing.JPanel SetDelayPanel;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JPanel TimePanel;
    private javax.swing.JLabel barInfoLabel;
    private javax.swing.JPanel barPanel;
    private javax.swing.JCheckBox compareDelayMultiplier;
    private javax.swing.JLabel compareDelayNumber;
    private javax.swing.JSlider compareDelaySlider;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JCheckBox getDelayMultiplier;
    private javax.swing.JLabel getDelayNumber;
    private javax.swing.JSlider getDelaySlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JLabel numComparesLabel;
    private javax.swing.JLabel numGetsLabel;
    private javax.swing.JLabel numSetsLabel;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JButton pauseResumeButton;
    private javax.swing.JMenuItem revertMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JCheckBox setDelayMultiplier;
    private javax.swing.JLabel setDelayNumber;
    private javax.swing.JSlider setDelaySlider;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusField;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton stepButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JPanel upperPanel;
    private BarArray mainArray;
    private DisplayThread displayManager;
    private StatsThread statsThread;
    private AbstractSearchSortThread activeThread;
    private File theFile;


}

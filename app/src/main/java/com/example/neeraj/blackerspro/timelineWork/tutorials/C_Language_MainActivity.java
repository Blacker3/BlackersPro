package com.example.neeraj.blackerspro.timelineWork.tutorials;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.timelineWork.messagesWroking.messageActivity;

import java.util.ArrayList;
import java.util.List;

public class C_Language_MainActivity extends AppCompatActivity implements TutorialAdapter.OnItemClickListener {

    private List<TutorialsTitle> tutorialsTitles = new ArrayList<>();
    private RecyclerView title_RV;
    private TutorialAdapter tutorialAdapter;
    private FloatingActionButton msgfloatBT;
    private String tutorial;
    private String[] cLangugaetitles = {"Introduction", "Advantages of C", "Structure of program", "Variables",
            "Operators", "Formatted IO - printf & scanf", "Chracter IO - getchar & putchar", "Features",
            "Conditional branching - if", "Conditional selection - switch", "Loops - while & for", "Local Jumps goto",
            "Break and Continue", "Function Basics", "Defination & Declaration", "Standard Header Files", "Blocks and Scope",
            "Variable Storage Classes", "Definition & Declaration", "Initialisation of Variables", "Arrays", "Pointer", "String",
            "Structure", "Union", "File Operations and Functions", "C Preprocessor directives"};

    private String[] JavaLangugaetitles = {"Introduction", "Abstract Class and Method", "Access Modifiers", "Access Protection",
            "Applet Lifecycle", "Applet Programming", "Applet Programming", "Basics Operators",
            "Built-in Exception", "Byte Stream Classes", "Character Stream Classes", "Commond Line Arugments",
            "Concept of Multithreading", "Concept of stream", "Constructor", "Creating Packages", "Creating Thread",
            "Declaring Member Variables", "Defining Packages", "Error", "Exception", "Extending Interfaces", "Extending Multiple Interfaces",
            "File Class", "Final Keyword", "Finally Statement", "Flow Control", "Garbage Collection", "Graphics programming",
            "Implementing Interfaces", "Import Statement", "Inheritance", "Interfaces", "Introduction of IO file", "Java API packages", "Java Array",
            "Java Components", "Life Cycle of Thread", "Method Overloading", "Method Overriding", "Methods", "Methods using in Applet", "Multiple Catch Statements", "Nested Classes", "Nested Interfaces",
            "Objects & Classes", "preparing to write applet", "Setting color in applet", "Setup for Java", "Static Members", "Stream Classes", "Super Keyword", "this keyword in Java", "Thowing our own Exception",
            "Thread Methods", "Thread Priorities", "Variables and Data Types", "Wrapper class in Java"};


    private String[] DataStructurestitles = {"Overview", "DSA Environment Setup", "DS Algorithms Basics", "DS and Algorithm Basic Concepts",
            "DS and Algorithms Arrays", "DS and Algorithms AVL Trees", "DS and Algorithms Binary Search", "DS and Algorithms Binary Search Tree",
            "DS and Algorithms Bubble Sort", "DS and Algorithms Circular Linked List", "DS and Algorithms Fibonacci Series", "DS and Algorithms Graph Data Structure",
            "DS and Algorithms Hash Table", "DS and Algorithms Insertion Sort", "DS and Algorithms Interpolation Search", "DS and Algorithms Linear Search", "DS and Algorithms Linked List",
            "DS and Algorithms Parsing Expressions", "DS and Algorithms Queue", "DS and Algorithms Quick Sort", "DS and Algorithms Recursion Basics", "DS and Algorithms Shell Sort", "DS and Algorithms Sorting Techniques",
            "DS and Algorithms Spanning Tree", "DS and Algorithms Stack", "DS and Algorithms Tower of Hanoi", "DS and Algorithms Tree", "DS and Algorithms Tree Traversal", "DS and Breadth First Traversal",
            "DS and Depth First Traversal", "DS and Merge Sort Algorithms", "DS Asymptotic Analysis", "DS Divide and Conquer", "DS Doubly linked list", "DS Dynamic Programming", "DS Greedy Algorithms",
            "DS Heap Data Structures",};


    private String[] Microroprocessortitles = {"Microprocessor - Overview", "8051 Input Output Ports", "8085 Architecture", "8085 Instruction Sets",
            "8085 Pin Configuration", "8086 Functional Units", "8086 Instruction Sets", "8086 Interrupts", "8086 Overview", "8086 Pin Configuration", "8255A - Programmable Peripheral Interface", "8279 - Programmable Keyboard"
            , "Addressing Modes & Interrupts", "Classification", "Intel 8253 - Programmable Interval Timer", "Intel 825354 - Operational Modes"
            , "I-O Interfacing Overview", "Microcontrollers - 8051 Architecture", "Microcontrollers - Overview"};

    private String[] Cplusplustitles = {"Advantages & Disadvantages", "Arrays", "Break and Continue", "cerr (error stream)",
            "cin (input stream)", "Classes Basics", "clog (log stream)", "Conditional branching - if",
            "Conditional selection - switch", "Constants - Literals", "Constructor - Destructor", "cout (output stream)",
            "Data Abstraction", "Data Encapsulation", "Declaration, call & argument", "Dynamic Memory", "Enumration and typedef",
            "Exception handling", "File Handling", "Friend Function", "Function Basics", "Function overloading", "Inheritance",
            "Inline function", "Intro to c++", "Loops - while & for", "Operator Overloading", "Operators", "Pointer",
            "Polymorphism & virtual function", "Preprocessor", "Random number", "Recursion", "Reference", "String", "Structure",
            "Structure of program", "Templates", "Variables", "Variable scope", "Variable scope"};


    private String[] Csharptitles = {"Arithmatic Operators", "Array Class", "ArrayList Class", "Arrays", " Assignment Operators", "Attributes",
            " Basic Syntax", "BitArray Class", "Bitwise Operators", "Break Statement", "Classes",
            "Collections", "Constants and Literals", "Continue Statement", "Data Types", "Decision Making",
            " Delegates", "Do...While Loop", " Encapsulation", "Enums", "Environment", "Events"
            , "Exception Handling", "File IO", "For Loop", "Generics", "Grouping Constructs", "Hashtable Class",
            "if...else Statement", "if Statement", "Indexers", "Inheritance", "Interfaces", "Introduction",
            "Jagged Arrays", "Logical Operators", "Loops", "Methods", "Miscellaneous Operators",
            " Multidimensional Arrays", "Multithreading", "Namespaces", " Nested if Statements", " Nested Loops",
            "nested switch Statements", "Nullables", "Operator Overloading", "Operators", "Operators Precedence",
            "Overview", "Passing Arrays as Function Arguments", "Passing Parameters by Output", "Passing Parameters by Reference",
            "Passing Parameters by Value", "Polymorphism", "Preprocessor Directives", "Program Structure", "Properties", "Quantifier",
            "Queue Class", "Reflection", "Regular Expressions", "Relational Operators", "SortedList Class",
            "Stack Class", "Strings", "Structures", "Switch Statement", "Type Conversion", "Variables", "While Loop"};







    private String[] AdvancedMicroprocessorstitles = {"Microprocessor_overview", "Microprocessor_8085_addressing_modes_and_interrupts", "Microprocessor_8085_architecture", "Microprocessor_8085_instruction_sets",
            "Microprocessor_8085_pin_configuration", "Microprocessor_8086_addressing_modes", "Microprocessor_8086_functional_units", "Microprocessor_8086_instruction_sets",
            "Microprocessor_8086_interrupts", "Microprocessor_8086_overview", "Microprocessor_8086_pin_configuration", "Microprocessor_8087_numeric_data_processor",
            "Microprocessor_8257_dma_controller", "Microprocessor_8257_dma_controller", "Microprocessor_8279_programmable_keyboard", "Microprocessor_classification", "Microprocessor_io_interfacing_overview",
            "Microprocessor_multiprocessor_configuration_overview"};



    private String[] MicroEmbeddedSystitles = {"Microcontrollers_overview", "Embedded Design", "Microcontrollers_8051_architecture", "Microcontrollers_8051_input_output_ports",
            "Microcontrollers_8051_interrupts", "Microcontrollers_8051_pin_description", "Microprocessor_discussion", "Microprocessor_intel_8253_8254_operational_modes",
            "Microprocessor_intel_8253_programmable_interval_timer", "Microprocessor_intel_8255a_pin_description", "Microprocessor_intel_8255a_programmable_peripheral_interface", "Microprocessor_quick_guide",
            "Microprocessor_useful_resources", "Microprocessor_useful_resources"};


    private String[] MicrowaveCommutitles = {"Introduction to Microwave Electronics", "Advantages And Dis-Advantages of using Microwave technology", "Applications and Uses of Microwave", "Importance of Microwave Antennas",
            "Principles of Satellite Communications", "RADAR Applications", "Satellite Communication", "Transmission Lines Vs Waveguides",
            "Types Of Antennas", "WAVE GUIDES"};



    private String[] OpticaFiberCommutitles = {"Introduction", "Detect", "Functional Advantages", "Losses",
            "Principles of Optical Fiber Communications", "Technology_uses"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorials_recycler_view);
        title_RV = findViewById(R.id.title_RV);
        tutorial = getIntent().getStringExtra("tutorial");


        tutorialAdapter = new TutorialAdapter(tutorialsTitles, C_Language_MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        title_RV.setLayoutManager(layoutManager);
        title_RV.setItemAnimator(new DefaultItemAnimator());
        msgfloatBT = findViewById(R.id.msgfloatBT);
        title_RV.setAdapter(tutorialAdapter);

        tutorialAdapter.setOnItemClickListner(C_Language_MainActivity.this);
        msgfloatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(C_Language_MainActivity.this, messageActivity.class);
                startActivity(intent);
            }
        });

        prepareTitlesData();
    }

    private void prepareTitlesData() {
        if(tutorial.equalsIgnoreCase("clanguage")) {
            for (int i = 1; i <= cLangugaetitles.length; i++) {


                TutorialsTitle tutorialsTitle = new TutorialsTitle(cLangugaetitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }

        else if(tutorial.equalsIgnoreCase("oopslanguage")){
            for (int i = 1; i <= Cplusplustitles.length; i++) {


                TutorialsTitle tutorialsTitle = new TutorialsTitle(Cplusplustitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }
        else if(tutorial.equalsIgnoreCase("java")){
            for (int i = 1; i <= JavaLangugaetitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(JavaLangugaetitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }
        else if(tutorial.equalsIgnoreCase("csharp")){
            for (int i = 1; i <= Csharptitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(Csharptitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }
        else if(tutorial.equalsIgnoreCase("datastruct")){
            for (int i = 1; i <= DataStructurestitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(DataStructurestitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }
        else if(tutorial.equalsIgnoreCase("micropro")){
            for (int i = 1; i <= Microroprocessortitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(Microroprocessortitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }

        else if(tutorial.equalsIgnoreCase("microwave")){
            for (int i = 1; i <= Microroprocessortitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(MicrowaveCommutitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }


        else if(tutorial.equalsIgnoreCase("microsys")){
            for (int i = 1; i <= MicroEmbeddedSystitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(MicroEmbeddedSystitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }


        else if(tutorial.equalsIgnoreCase("optical")){
            for (int i = 1; i <= OpticaFiberCommutitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(OpticaFiberCommutitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }


        else if(tutorial.equalsIgnoreCase("advancedmicro")){
            for (int i = 1; i <= AdvancedMicroprocessorstitles.length; i++) {

                TutorialsTitle tutorialsTitle = new TutorialsTitle(AdvancedMicroprocessorstitles[i - 1], Integer.toString(i));

                tutorialsTitles.add(tutorialsTitle);
            }
        }
        else{

                for (int i = 1; i <= cLangugaetitles.length; i++) {

                    TutorialsTitle tutorialsTitle = new TutorialsTitle(cLangugaetitles[i - 1], Integer.toString(i));

                    tutorialsTitles.add(tutorialsTitle);
                }



        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(C_Language_MainActivity.this, C_content_MainActivity.class);
        intent.putExtra("tutorial",tutorial);
        intent.putExtra("count", Integer.toString(position + 1));
        startActivity(intent);

    }
}

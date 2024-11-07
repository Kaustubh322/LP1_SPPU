import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MacroProcessor {
    private final Map<String, Macro> macroTable = new HashMap<>();
    private final List<String> expandedCode = new ArrayList<>();

    // Pass-I: Define Macros
    public void passOne(String[] code) {
        Macro currentMacro = null;
        boolean isMacroDefinition = false;

        for (String line : code) {
            String[] tokens = line.split("\\s+");

            // Check for MACRO directive
            if (tokens[0].equals("MACRO")) {
                isMacroDefinition = true;
                currentMacro = new Macro(tokens[1]);
            }
            // Check for MEND directive (end of macro)
            else if (tokens[0].equals("MEND")) {
                isMacroDefinition = false;
                if (currentMacro != null) {
                    macroTable.put(currentMacro.getName(), currentMacro);
                }
                currentMacro = null;
            }
            // Inside a macro definition
            else if (isMacroDefinition && currentMacro != null) {
                currentMacro.addLine(line);
            }
        }
    }

    // Pass-II: Expand Macros
    public void passTwo(String[] code) {
        for (String line : code) {
            String[] tokens = line.split("\\s+");

            if (macroTable.containsKey(tokens[0])) {
                Macro macro = macroTable.get(tokens[0]);
                expandedCode.addAll(macro.expand(tokens));
            } else {
                expandedCode.add(line); // Regular line
            }
        }
    }

    public void displayExpandedCode() {
        for (String line : expandedCode) {
            System.out.println(line);
        }
    }

    static class Macro {
        private final String name;
        private final List<String> body = new ArrayList<>();

        public Macro(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addLine(String line) {
            body.add(line);
        }

        public List<String> expand(String[] arguments) {
            List<String> expandedLines = new ArrayList<>();
            for (String line : body) {
                String expandedLine = line;
                for (int i = 1; i < arguments.length; i++) {
                    expandedLine = expandedLine.replace("#" + i, arguments[i]);
                }
                expandedLines.add(expandedLine);
            }
            return expandedLines;
        }
    }

    public static void main(String[] args) {
        String[] code = {
                "MACRO ADDM", "LOAD #1", "ADD #2", "STORE #1", "MEND",
                "START", "ADDM A B", "END"
        };

        MacroProcessor processor = new MacroProcessor();
        processor.passOne(code);   // Perform Pass-I to define macros
        processor.passTwo(code);   // Perform Pass-II to expand macros

        System.out.println("Expanded Code:");
        processor.displayExpandedCode();
    }
}

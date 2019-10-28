/*
 * NAME: Jaosn Chau
 * PID:  A14388619
 */



/**
 * Gene Splicing CRISPR Simulator
 *
 * @author Jason chau
 * @since 10/29/2019
 */
public class CRISPR {

    /*Sequences to use/test your CRISPR functions on. Please add more as you test*/
    private static String simpleGenome = "ACATATA";

    private static String sequencedGenome = "AAATTCAAGCGAGGTGATTACAACAAATTTTGCTGATGGTTTAGGCGTA"
            + "CAATCCCCTAAAGAATATAATTAAGAAAATAGCATTCCTTGTCGCCTAGAATTACCTACCGGCGTCCACCATACCTTCG"
            + "ATATTCGCGCCCACTCTCCCATTAGTCGGCACAAGTGGATGTGTTGCGATTGCCCGCTAAGATATTCTAAGGCGTAACG"
            + "CAGATGAATATTCTACAGAGTTGCCGTACGCGTTGAACACTTCACGGATGATAGGAATTTGCGTATAGAGCGGGTCATT"
            + "GAAGGAGATTACACTCGTAGTTAACAACGGGCCCGGCTCTATCAGAACACGAGTGCCTTGAATAACATACTCATCACTA";

    private static String overlappingGuide = "UAU";
    private static String guideRNA = "CUAAUGU";
    private static String splicedGene = "TAGACAT";


    /**
     * Program Entry, this simply runs
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        /*Should print out ACATATA (unchanged)*/
        System.out.println(spliceDNA(simpleGenome, overlappingGuide, splicedGene));
        simpleGenome = "ATATATAT";
        guideRNA= "UA";
        splicedGene = "CAT";
        System.out.println(spliceDNA(simpleGenome,guideRNA,splicedGene));
        simpleGenome = "ATATA";
        guideRNA= "CG";
        splicedGene = "CAT";
        System.out.println(spliceDNA(simpleGenome,guideRNA,splicedGene));
        simpleGenome = "ACATATA";
        guideRNA= "UA";
        splicedGene = "CAT";
        System.out.println(spliceDNA(simpleGenome,guideRNA,splicedGene));

    }

    /**
     *  Simulate gene splicing on a genome using CRISPR
     *
     * @param genomeSequence initial DNA encoding
     * @param guideSequence guideRNA encoding
     * @param splicedSequence target insertion gene encoding
     * @return modified genome
     */
    public static String spliceDNA(String genomeSequence, String guideSequence, String splicedSequence) {
        DoublyLinkedList<Character> genome = new DoublyLinkedList<>();
        DoublyLinkedList<Character> guideRNA = new DoublyLinkedList<>();

        populateFromDNA(genome, genomeSequence);
        populateDNAFromRNA(guideRNA, guideSequence);


        // get locations of the starting positions of the matched guidesequence
        //initial posiion has to be at least length size away from initial position
        int[] matchedpos = genome.match(guideRNA);
        for (int x =0; x < matchedpos.length; x++){
            //if last position
            if (x+1 > matchedpos.length -1){
                break;
            }
            if (matchedpos[x] + guideRNA.size() > matchedpos[x+1] && matchedpos[x] != -1){
                matchedpos[x] = -1;
                matchedpos[x+1] = -1;
            }
        }
        int cont;

        DoublyLinkedList<Character> spliced = new DoublyLinkedList<>();
        populateFromDNA(spliced,splicedSequence);
        int sizer = 0;
        for(int x = 0; x < matchedpos.length; x++){
            cont = matchedpos[x];
            if (cont == -1){
                continue;
            }


            sizer += guideSequence.length();
            genome.splice(cont+sizer,spliced);
            sizer++;
        }


        return transcribeGeneticCode(genome);
    }

    /**
     * This is a direct encoding of the genetic code from the String to a LinkedList
     * @param dnaList to populate
     * @param dnaString DNA string encoding
     */
    public static void populateFromDNA(DoublyLinkedList<Character> dnaList, String dnaString) {
        //TODO: Populate dnaList with the characters in s
        char[] chars = dnaString.toCharArray();
        for (int x = 0; x < chars.length; x++){
            dnaList.add(chars[x]);
        }
    }

    /**
     * This is an encoding of of the DNA that binds with the RNA
     * Remember that DNA pairs up A-T C-G, and RNA pairs up A-U C-G
     * Thus the guide RNA AUCG would match with the DNA TAGC
     * @param dnaList to populate
     * @param rnaString RNA string encoding
     */
    public static void populateDNAFromRNA(DoublyLinkedList<Character> dnaList, String rnaString) {
        char[] chars = rnaString.toCharArray();
        int counter;
        String rna = "AUCG";
        String dna = "TAGC";

        char[] conversion = rna.toCharArray();
        char[] unconverted = dna.toCharArray();
        for (int x = 0; x< chars.length; x++){
            for (int y = 0; y < conversion.length; y++){
                if (chars[x] == conversion[y]){
                    dnaList.add(unconverted[y]);
                    break;
                }
            }
        }

    }

    /**
     * Recreate the original base sequence that was loaded into the list
     * @param geneticSequence list representation of the
     * @return base sequence of the genetic material
     */
    public static String transcribeGeneticCode(DoublyLinkedList<Character> geneticSequence) {
        String s = "";
        for (char c : geneticSequence) {
            s += c;
        }
        return s;
    }

}

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SpamMail {
    int wordCount[];
    int charCount=0;
    double spamData[],totalWords=0;
    int capital_run_length_total=0,capital_run_length_longest=0, charRunLength=0, maxCharRunLength=0;
    double capital_run_length_average=0.0;
    boolean isSpam;

    public void computeIsSpam() {
    	if(this.capital_run_length_average>251||this.maxCharRunLength>3) {
    		isSpam=true;
    	}
        else if(spamData[26]>1) {  //george
			isSpam=false;
		}
		else if(spamData[18]>2) {  //you
			isSpam=true;
		}
		else if(spamData[20]>1) { //your
			isSpam=true;
		}
		else if(spamData[24]>0.5) { //HP
			isSpam=false;
		}
		else if(spamData[15]>0.3) { //free
			isSpam=true;
		}
		else if(spamData[25]>0.3) { //HPL
			isSpam=false;
		}
		/*else if(spamData[51]>0.35) { // !
			isSpam=true;
		}*/
		else if(spamData[12]>0.1||spamData[10]>0.1||spamData[8]>0.1||spamData[3]>0.1||spamData[5]>0.1||spamData[6]>0.1||spamData[7]>0.1||spamData[0]>0.1||spamData[14]>0.1||spamData[16]>0.1||spamData[19]>0.1) {
			isSpam=true;
		}
		else if(spamData[17]<0.15||spamData[9]<0.15||spamData[4]<0.15) {
			isSpam=false;
		}
		else if(spamData[45]>0.25||spamData[44]>0.25) {
			isSpam=false;
		}
		else if(spamData[35]>0.1||spamData[34]>0.1||spamData[32]>0.1||spamData[30]>0.1||spamData[29]>0.1||spamData[28]>0.1) {
			isSpam=false;
		}
		else if(spamData[23]>0.15||spamData[22]>0.15||spamData[21]>0.15) {
			isSpam=true;
		}
		else if(spamData[41]>0.16||spamData[36]>0.16||spamData[27]>0.16) {
			isSpam=false;
		}
		else if(spamData[43]>0.1) {
			isSpam=false;
		}
		else {
			isSpam=false;
		}
	}

	public void printMap(Map mp) {
		for(int i=0;i<58;i++) {
		    System.out.println((i+1)+"       "+spamData[i]+"       "+wordCount[i]);
		}
		System.out.println("capital_run_length_average= "+capital_run_length_average);
		System.out.println("capital_run_length_longest= "+capital_run_length_longest);
		System.out.println("capital_run_length_total= "+capital_run_length_total);
		System.out.println("Total Words= "+totalWords);
		System.out.println("Total Chars= "+charCount);
    	}

    public void readFile(File file) {
        String splitBy = " ";
        int capitalRunLength=0;
        Vector<Integer> capitalLettersInEachWord=new Vector<Integer>();
        HashMap<String, Integer> spamlist = new HashMap<String, Integer>();
        BufferedReader br = null;
        String[] temp;
        wordCount=new int[58];
        spamData=new double[62];
        boolean capitalFlag=false;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(file));

            //read the input file
            while ((sCurrentLine = br.readLine()) != null) {
                temp = (sCurrentLine.split(splitBy));
                totalWords+=temp.length;
                
                for(int i =0; i< temp.length; i++){
                	if(temp[i].equals("!")||temp[i].contains("(")||temp[i].contains("[")||temp[i].contains("#")||temp[i].contains("$")
                		||temp[i].contains(";")||temp[i].contains("_")||temp[i].contains("!")||temp[i].contains("/")||temp[i].contains("\\")) {
                		totalWords--;
                	}
                    if(temp[i].toLowerCase().equals("make")) {
                        wordCount[0]+=1;
                    }

                    else if(temp[i].toLowerCase().equals("address")){
                        wordCount[1]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("all")){
                        wordCount[2]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("3d")){
                        wordCount[3]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("our")){
                        wordCount[4]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("over")){
                        wordCount[5]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("remove")){
                        wordCount[6]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("internet")){
                        wordCount[7]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("order")){
                        wordCount[8]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("mail")){
                        wordCount[9]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("receive")){
                        wordCount[10]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("will")){
                        wordCount[11]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("people")){
                        wordCount[12]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("report")){
                        wordCount[13]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("addresses")){
                        wordCount[14]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("free")){
                        wordCount[15]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("business")){
                        wordCount[16]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("email")){
                        wordCount[17]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("you")){
                        wordCount[18]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("credit")){
                        wordCount[19]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("your")){
                        wordCount[20]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("font")){
                        wordCount[21]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("000")){
                        wordCount[22]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("money")){
                        wordCount[23]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("hp")){
                        wordCount[24]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("hpl")){
                        wordCount[25]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("george")){
                        wordCount[26]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("650")){
                        wordCount[27]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("lab")){
                        wordCount[28]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("labs")){
                        wordCount[29]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("telnet")){
                        wordCount[30]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("857")){
                        wordCount[31]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("data")){
                        wordCount[32]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("415")){
                        wordCount[33]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("85")){
                        wordCount[34]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("technology")){
                        wordCount[35]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("1999")){
                        wordCount[36]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("parts")){
                        wordCount[37]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("pm")){
                        wordCount[38]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("direct")){
                        wordCount[39]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("cs")){
                        wordCount[40]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("meeting")){
                        wordCount[41]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("original")){
                        wordCount[42]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("project")){
                        wordCount[43]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("re")){
                        wordCount[44]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("edu")){
                        wordCount[45]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("table")){
                        wordCount[46]+=1;
                    }
                    else if(temp[i].toLowerCase().equals("conference")){
                        wordCount[47]+=1;
                    }
		    System.out.println(temp[i].length()+"asdas");
                    for (int i1 = 0; i1 < temp[i].length(); i1++) {

                        char ch = temp[i].charAt(i1);
                        System.out.println("ch is "+ch);
                        System.out.println("cc is "+charCount);
                        if(ch==';') {
                            charCount++;
                            wordCount[48]+=1;
                        }
                        if(ch=='(') {
                            charCount++;
                            wordCount[49]+=1;
                        }

                        if(ch=='[') {
                            charCount++;
                            wordCount[50]+=1;
                        }

                        if(ch=='!') {
                            charCount++;
                            wordCount[51]+=1;
                        }

                        if(ch=='$') {
                            charCount++;
                            wordCount[52]+=1;
                        }

                        if(ch=='#') {
                            charCount++;
                            wordCount[53]+=1;
                        }

                        if(ch=='\\') {
                            charCount++;
                            wordCount[54]+=1;
                        }

                        if(ch=='_') {
                            charCount++;
                            wordCount[55]+=1;
                        }

                        if(ch=='/') {
                            charCount++;
                            wordCount[56]+=1;
                        }

                        if(ch=='-') {
                            charCount++;
                            wordCount[57]+=1;
                        }

                    }
                    //check for uppercase letters


                    for(int i1=0;i1<temp[i].length();i1++) {
                    	if(temp[i].charAt(i1)==';'||temp[i].charAt(i1)=='!'||temp[i].charAt(i1)=='('||
                    			temp[i].charAt(i1)=='['||temp[i].charAt(i1)=='$'||temp[i].charAt(i1)=='#'||
                    			temp[i].charAt(i1)=='\\'||temp[i].charAt(i1)=='-'||temp[i].charAt(i1)=='_'||
                    			temp[i].charAt(i1)=='/') {
                    		charRunLength++;
                    		System.out.println("Char run length "+charRunLength);
                    	}
                    	else {
                    		if(charRunLength>maxCharRunLength) {
                    			maxCharRunLength=charRunLength;                    		
                    			
                    		}
                    		charRunLength=0;
                    	}
                        //if word is uppercase
                        if(Character.isUpperCase(temp[i].charAt(i1))) {
                            capitalFlag=true;
                            //calculate capital_run_length_total
                            capital_run_length_total++;
                            capitalRunLength++;
                        }
                        else {
                            if(capitalFlag==true) {
                                capitalFlag=false;
                                capitalLettersInEachWord.add(capitalRunLength);
                                capitalRunLength=0;
                            }
                        }
                    }
                    //calculate capital_run_length_longest
                    if(capitalRunLength>capital_run_length_longest)
                        capital_run_length_longest=capitalRunLength;
                }
            }
            //end of words n file and the last letter is a character
            if(charRunLength>maxCharRunLength) {
    			maxCharRunLength=charRunLength;                    		
    			
    		}
            //end of words in file and the last letter is also a capital letter
            if(capitalFlag==true) {
                capitalFlag=false;
                capitalLettersInEachWord.add(capitalRunLength);
                capitalRunLength=0;
            }

            //calculating capital run length average
            double sum=0;
            System.out.println("size of vector="+capitalLettersInEachWord.size());
            for(int capitalLetterLengthForWord:capitalLettersInEachWord) {
                sum+=capitalLetterLengthForWord;
                System.out.println(capitalLetterLengthForWord);
            }
            System.out.println("-----------"+sum+" "+capitalLettersInEachWord.size()+"   @!!!@@@#");
            capital_run_length_average=sum/capitalLettersInEachWord.size();
            

            //making the final spam dataset
            for(int i=0;i<48;i++) {
                spamData[i]=(wordCount[i]/totalWords);
            }
            for(int i=48;i<58;i++) {
                spamData[i]=((double)wordCount[i]/charCount);
                System.out.println((double)wordCount[i]/charCount);
            }

            double temp1=wordCount[0]/totalWords;
            spamData[58]=capital_run_length_average;
            spamData[59]=capital_run_length_longest;
            spamData[60]=capital_run_length_total;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //return spamlist;
    }

}

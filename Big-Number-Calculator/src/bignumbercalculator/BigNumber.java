/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bignumbercalculator;

/**
 *
 * @author adari
 */
public class BigNumber {
    
    private SLL digits1 = new SLL();
    private SLL digits2 = new SLL();
    private int decimalPointPosition;

    public boolean negative = false;
    public boolean inputErr = false;
    
    public BigNumber(String s)
    {
        boolean hasDecimal = false;

        if(s.isEmpty()) {
                inputErr = true;
                return;
            }
                    
        if(s.charAt(0) == '-' && s.length() > 1) {
            negative = true;
            s = s.substring(1);
            while(s.charAt(0) == '0' && s.length() > 1)
                s = s.substring(1);
            if(s.charAt(0) == '.')
                s = "0" + s;
            int pointer = s.length();
            while(s.contains(".") && s.charAt(pointer - 1) == '0') {
                pointer = pointer - 1;
            }
            if(s.charAt(pointer - 1) == '.')
                pointer = pointer - 1;
            s = s.substring(0, pointer);
            if(!s.matches("^\\d*(\\.\\d+)?$")) {
                inputErr = true;
                return;
            }
        }
        
        else if(s.charAt(0) == '-' && s.length() == 1 || s.charAt(0) == '.' && s.length() == 1) {
            inputErr = true;
            return;
        }
        else {
            while(s.charAt(0) == '0' && s.length() > 1)
                s = s.substring(1);
            if(s.charAt(0) == '.')
                s = "0" + s;
            int pointer = s.length();
            while(s.contains(".") && s.charAt(pointer - 1) == '0') {
                pointer = pointer - 1;
            }
            if(s.charAt(pointer - 1) == '.')
                pointer = pointer - 1;
            s = s.substring(0, pointer);
            if(!s.matches("^\\d*(\\.\\d+)?$")) {
                inputErr = true;
                return;
            }
        }
        
        for(int x = 1; x <= s.length(); x++)
        {
            if(s.charAt(x-1) == '.')
            {
                hasDecimal = true;
                decimalPointPosition = x - 1;
                continue;
            }
            if(hasDecimal == true)
            {               
                SLLNode node = new SLLNode(s.charAt(x-1), null);
            
                if(x == decimalPointPosition + 2){
                    digits2.setFirst(node);
                }
                else    
                    digits2.insertAtFront(node);
            }
            else{
                SLLNode node = new SLLNode(s.charAt(x-1), null);
           
                if(x == 1)
                    digits1.setFirst(node);
                else    
                    digits1.insertAtFront(node);
            }
            
        }
    }
    
    public SLL getDigits1() {
        return this.digits1;
    }
    
    public SLL getDigits2() {
        return this.digits2;
    }
    
    public BigNumber abs() {
        BigNumber n = new BigNumber("");
        n.digits1 = this.digits1;
        n.digits2 = this.digits2;
        n.negative = this.negative;
        if(n.negative)
            n.negative = false;
        return n;
    }
    
    public String sciNot(String str){
        int MAX_LENGTH = 43;
        int count = 0;
        String sci_str = "";
        if(str.length() > MAX_LENGTH && str.contains(".") && str.charAt(0) == '0') {
            for(int i = 2; i < str.length(); i++)
                if(str.charAt(i) != '0') {
                    count = i - 1;
                    break;
                }
            System.out.println(count);
            sci_str = sci_str + str.charAt(count + 1) + ".";
            for(int i = count + 2; i < Math.min(MAX_LENGTH + count - 4, str.length()); i++)
                sci_str = sci_str + str.substring(i, i+1);
            sci_str = sci_str + "e" + "-" + Integer.toString(count);
        }
        else if(str.length() > MAX_LENGTH && str.contains(".") && str.charAt(0) != '0' && str.charAt(0) != '-') {
            for(int i = 0; i < str.length(); i++)
                if(str.charAt(i) == '.') {
                    count = i - 1;
                    str = str.substring(0, i) + str.substring(i + 1);
                }
            sci_str = sci_str + str.charAt(0) + ".";
            for(int i = 1; i < MAX_LENGTH - 4; i++)
                sci_str = sci_str + str.substring(i, i+1);
            sci_str = sci_str + "e" + "+" +Integer.toString(count);
        }
        else if(str.length() > MAX_LENGTH && !str.contains(".") && str.charAt(0) != '-') {
            count = str.length() - 1;
            sci_str = sci_str + str.charAt(0) + ".";
            for(int i = 1; i < MAX_LENGTH - 4; i++)
                sci_str = sci_str + str.substring(i, i+1);
            sci_str = sci_str + "e" + "+" +Integer.toString(count);
        }
        else if(str.length() > MAX_LENGTH && str.contains(".") && str.charAt(1) == '0' && str.charAt(0) == '-') {
            for(int i = 3; i < str.length(); i++)
                if(str.charAt(i) != '0') {
                    count = i - 2;
                    break;
                }
            sci_str = sci_str + str.charAt(count + 2) + ".";
            for(int i = count + 3; i < Math.min(MAX_LENGTH + count - 4, str.length()); i++)
                sci_str = sci_str + str.substring(i, i+1);
            sci_str = sci_str + "e" + "-" + Integer.toString(count);
        }
        else if(str.length() > MAX_LENGTH && str.contains(".") && str.charAt(1) != '0' && str.charAt(0) == '-') {
            for(int i = 0; i < str.length(); i++)
                if(str.charAt(i) == '.') {
                    count = i - 2;
                    str = str.substring(0, i) + str.substring(i + 1);
                }
            sci_str = sci_str + "-" + str.charAt(1) + ".";
            for(int i = 2; i < MAX_LENGTH - 4; i++)
                sci_str = sci_str + str.substring(i, i+1);
            sci_str = sci_str + "e" + "+" +Integer.toString(count);
        }
        else if(str.length() > MAX_LENGTH && !str.contains(".") && str.charAt(0) == '-') {
            count = str.length() - 2;
            sci_str = sci_str + "-" +str.charAt(1) + ".";
            for(int i = 2; i < MAX_LENGTH - 4; i++)
                sci_str = sci_str + str.substring(i, i+1);
            sci_str = sci_str + "e" + "+" +Integer.toString(count);
        }
        else
            sci_str = str;
        return sci_str;
    }
    
    public String toString()
    {
        String bigNumber = "";
        
        if(digits2.getFirst() != null)
        {
            for(SLLNode current = digits2.getFirst(); current != null; current = current.snode)
            {
            String s = current.element.toString();
            bigNumber = s + bigNumber;
            }
        
            bigNumber = "." + bigNumber;
        }
        
        for(SLLNode current = digits1.getFirst(); current != null; current = current.snode)
        {
            String s = current.element.toString();
            bigNumber = s + bigNumber;
        }
        
        if(negative)
            bigNumber = "-" + bigNumber;
        return bigNumber;
    }
    
    public BigNumber add(BigNumber bn)
    {
        SLLNode digits1curr = this.digits1.getFirst();
        SLLNode digits1curr2 = bn.digits1.getFirst();
        SLLNode digits2curr = this.digits2.getFirst();
        SLLNode digits2curr2 = bn.digits2.getFirst();
        
        SLL integerPart = new SLL();
        SLL decimalPart = new SLL();
        if(this.digits1.getLength() >= bn.digits1.getLength())
        {
            for(; digits1curr2 != null; digits1curr2 = digits1curr2.snode)
            {
                digits1curr.element = (char)((char)digits1curr2.element + (char)digits1curr.element - '0');
                digits1curr = digits1curr.snode;
            }
            integerPart.setFirst(this.digits1.getFirst());
        }
        else
        {
            for(; digits1curr != null; digits1curr = digits1curr.snode)
            {
                digits1curr2.element = (char)((char)digits1curr.element + (char)digits1curr2.element - '0');
                digits1curr2 = digits1curr2.snode;
            }
            integerPart.setFirst(bn.digits1.getFirst());
        }
        
        if(this.digits2.getFirst() == null && bn.digits2.getFirst() == null);
        else if(this.digits2.getLength() >= bn.digits2.getLength())
        {
            for(int i = this.digits2.getLength() - bn.digits2.getLength(); i > 0; i--)
            {
                digits2curr = digits2curr.snode;
            }
            for(; digits2curr2 != null; digits2curr2 = digits2curr2.snode)
            {
                digits2curr.element = (char)((char)digits2curr2.element + (char)digits2curr.element - '0');
                digits2curr = digits2curr.snode;
            }
            decimalPart.setFirst(this.digits2.getFirst());
        }
        else
        {
            for(int i = bn.digits2.getLength() - this.digits2.getLength(); i > 0; i--)
            {
                digits2curr2 = digits2curr2.snode;
            }
            for(; digits2curr != null; digits2curr = digits2curr.snode)
            {
                digits2curr2.element = (char)((char)digits2curr.element + (char)digits2curr2.element - '0');
                digits2curr2 = digits2curr2.snode;
            }
            decimalPart.setFirst(bn.digits2.getFirst());
        }
        
        for(SLLNode here = decimalPart.getFirst(); here != null; here = here.snode)
        {
            if((char)here.element >= ('0' + 10) && here.snode != null)
            {
                here.element = (char)((char)here.element - 10);
                here.snode.element = (char)((char)here.snode.element + 1);
            }
            else if((char)here.element >= ('0' + 10) && here.snode == null)
            {
                here.element = (char)((char)here.element - 10);
                integerPart.getFirst().element = (char)((char)integerPart.getFirst().element + 1);
            }
            else;
        }
        
        for(SLLNode here = integerPart.getFirst(); here != null; here = here.snode)
        {
            if((char)here.element >= ('0' + 10) && here.snode != null)
            {
                here.element = (char)((char)here.element - 10);
                here.snode.element = (char)((char)here.snode.element + 1);
            }
            else if((char)here.element >= ('0' + 10) && here.snode == null)
            {
                here.element = (char)((char)here.element - 10);
                SLLNode newNode = new SLLNode('1', null);
                integerPart.insertAtFront(newNode);
            }
            else;
        }
        BigNumber result = new BigNumber("");
        result.digits1.setFirst(integerPart.getFirst());
        result.digits2.setFirst(decimalPart.getFirst());
        return result;
    }
    
    public BigNumber subtract(BigNumber bn)
    {
        int length1 = Math.max(bn.digits1.getLength(),digits1.getLength());
        int length2 = Math.max(bn.digits2.getLength(),digits2.getLength());
 
        bn.digits1.modifyInt(length1);
        
        digits2.modifyDec(length2);
        bn.digits2.modifyDec(length2);
        
        digits2.concatSLL(digits1);
        bn.digits2.concatSLL(bn.digits1);

        SLL temp = new SLL();
        int ad = 0;
        
        SLLNode N1=digits2.getFirst();
        SLLNode N2=bn.digits2.getFirst();

        int d2;
        int d;
        int d_snode;
        SLLNode cur;
        if(digits2.getLength() == 1 && bn.digits2.getLength() == 1)
        {
            d2 = Integer.parseInt(N2.element.toString());
            d = Integer.parseInt(N1.element.toString());
            cur = new SLLNode(d-d2,null);
            temp.setFirst(cur);
        }
        else {
            d2 = Integer.parseInt(N2.element.toString());
            d = Integer.parseInt(N1.element.toString())+10;
        
        
            d_snode = Integer.parseInt(N1.snode.element.toString())-1;
            N1.snode.element = Integer.toString(d_snode);
        
            cur = new SLLNode((d-d2+ad)%10,null);
            temp.setFirst(cur);
            ad = (d-d2+ad)/10;
            N2=N2.snode;
            N1=N1.snode;
        }
        
        for(;N1.snode!=null;N2=N2.snode,N1=N1.snode)
        {                
            d2 = Integer.parseInt(N2.element.toString());
             d = Integer.parseInt(N1.element.toString())+10;
                
             d_snode = Integer.parseInt(N1.snode.element.toString())-1;
             N1.snode.element = Integer.toString(d_snode); 
                
             cur = new SLLNode((d-d2+ad)%10,null);
             temp.insertAtFront(cur);
             ad = (d-d2+ad)/10;        
        }   
        
        if(digits2.getLength() != 1 && bn.digits2.getLength() != 1){
            d2 = Integer.parseInt(N2.element.toString());
            d = Integer.parseInt(N1.element.toString());

            cur = new SLLNode((d-d2+ad),null);
            temp.insertAtFront(cur);
        }
        
        int de = 0;
        String dm="";
        for(SLLNode dis=temp.getFirst();dis!=null;dis=dis.snode){
             de++;
             if(de == length1+1)
             dm = dm+".";                  
             dm=dm+dis.element.toString();
        }
        BigNumber result = new BigNumber(dm);
        return result;
    }
    
    public BigNumber multiply(BigNumber bn)
    {
        int i=digits2.getLength()+bn.digits2.getLength();
        
        // get input
        digits2.concatSLL(digits1);
        bn.digits2.concatSLL(bn.digits1);
        SLLNode N1=digits2.getFirst();
        SLLNode N2=bn.digits2.getFirst();

        SLL temp = new SLL();
        int rec1=0;
        SLLNode tp=null;
        for(;N2!=null;N2=N2.snode)
        {
            int d=Integer.parseInt(N2.element.toString());
            SLLNode cur=temp.getFirst();
            int ad=0;
            int rec2=0;
            N1=digits2.getFirst();
            for(;N1!=null;N1=N1.snode)
            {      
                int d2=Integer.parseInt(N1.element.toString());
                if(rec2+rec1==0){                
                    cur=new SLLNode((d*d2+ad)%10,null);
                    temp.setFirst(cur);
                    ad=(d*d2+ad)/10;
                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                
                    }                  
                }                   
                else if(rec1==0){                           
                    temp.insertAtEnd((d*d2+ad)%10);                       
                    ad=(d*d2+ad)/10;

                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                
                    }                            
                }
                else if(rec1!=0 && rec2 ==0){

                    for(int h=1;h<=rec1;h++){
                        if(cur.snode==null)
                            cur.snode=new SLLNode(0,null);
                        cur=cur.snode;
                    }                  
                    tp=cur;
                    int q=(int)tp.element;
                    temp.replace((d*d2+ad+q)%10,tp);
                    ad=(d*d2+ad+q)/10;
                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                               
                    }
                }
                else if(rec1!=0 && rec2!=0){
                    if(tp.snode==null)
                        tp.snode=new SLLNode(0,null);
                    tp=tp.snode;
                    int q=(int)tp.element;
                    temp.replace((d*d2+ad+q)%10,tp);
                    ad=(d*d2+ad+q)/10;
                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                
                    }                 
                }
                rec2++;                  
            }
            rec1++;        
        }
        int de=-1;
        String dm="";
        for(SLLNode dis=temp.getFirst();dis!=null;dis=dis.snode){
            de++;
            if(de==i && i!=0)
                dm="."+dm;
            dm=dis.element.toString()+dm;
        }
        BigNumber result = new BigNumber(dm);
        return result;
    }
    
    public BigNumber divide(BigNumber bn)
    {
        float i=digits2.getLength()+bn.digits2.getLength();
        
        digits2.concatSLL(digits1);
        bn.digits2.concatSLL(bn.digits1);
        SLLNode N1=digits2.getFirst();
        SLLNode N2=bn.digits2.getFirst();

        SLL temp = new SLL();
        float rec1=0;
        SLLNode tp=null;
        for(;N2!=null;N2=N2.snode)
        {
            float d=Integer.parseInt(N2.element.toString());
            SLLNode cur=temp.getFirst();
            float ad=0;
            float rec2=0;
            N1=digits2.getFirst();
            for(;N1!=null;N1=N1.snode)
            {      
                float d2=Integer.parseInt(N1.element.toString());
                if(rec2+rec1==0){                
                    cur=new SLLNode((d/d2+ad)%10,null);
                    temp.setFirst(cur);
                    ad=(d/d2+ad)/10;
                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                
                    }                  
                }                   
                else if(rec1==0){                           
                    temp.insertAtEnd((d/d2+ad)%10);                       
                    ad=(d/d2+ad)/10;

                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                
                    }                            
                }
                else if(rec1!=0 && rec2 ==0){

                    for(int h=1;h<=rec1;h++){
                        if(cur.snode==null)
                            cur.snode=new SLLNode(0,null);
                        cur=cur.snode;
                    }                  
                    tp=cur;
                    float q=(int)tp.element;
                    temp.replace((d/d2+ad+q)%10,tp);
                    ad=(d/d2+ad+q)/10;
                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                               
                    }
                }
                else if(rec1!=0 && rec2!=0){
                    if(tp.snode==null)
                        tp.snode=new SLLNode(0,null);
                    tp=tp.snode;
                    int q=(int)tp.element;
                    temp.replace((d/d2+ad+q)%10,tp);
                    ad=(d/d2+ad+q)/10;
                    if(ad!=0 && N1.snode==null){
                        temp.insertAtEnd(ad);                
                    }                 
                }
                rec2++;                  
            }
            rec1++;        
        }
        int de=-1;
        String dm="";
        for(SLLNode dis=temp.getFirst();dis!=null;dis=dis.snode){
            de++;
            if(de==i && i!=0)
                dm="."+dm;
            dm=dis.element.toString()+dm;
        }
        BigNumber result = new BigNumber(dm);
        return result;
    }
    
    public String plus(BigNumber bn) {
        if(this.negative && bn.negative) {
            BigNumber result = this.abs().add(bn.abs());
            result.negative = true;
            return sciNot(result.toString());
        }
        else if(!this.negative && bn.negative && !this.lessThan(bn.abs())) {
            BigNumber result = this.subtract(bn.abs());
            result.negative = false;
            return result.toString();
        }
        else if(!this.negative && bn.negative && this.lessThan(bn.abs())) {
            BigNumber result = bn.abs().subtract(this);
            result.negative = true;
            return result.toString();
        }
        else if(this.negative && !bn.negative && this.abs().greaterThan(bn)) {
            BigNumber result = this.abs().subtract(bn);
            result.negative = true;
            return result.toString();
        }
        else if(this.negative && !bn.negative && !(this.abs().greaterThan(bn))) {
            BigNumber result = bn.subtract(this.abs());
            result.negative = false;
            return result.toString();
        }
        else {
            BigNumber result = this.add(bn);
            result.negative = false;
            return sciNot(result.toString());
        }
    }
    
    public String minus(BigNumber bn) {
        if(this.negative && !bn.negative) {
            BigNumber result = this.abs().add(bn);
            result.negative = true;
            return sciNot(result.toString());
        }
        else if(this.negative && bn.negative && this.abs().greaterThan(bn.abs())) {
            BigNumber result = this.abs().subtract(bn.abs());
            result.negative = true;
            return result.toString();
        }
        else if(this.negative && bn.negative && !(this.abs().greaterThan(bn.abs()))) {
            BigNumber result = bn.abs().subtract(this.abs());
            result.negative = false;
            return result.toString();
        }
        else if(!this.negative && !bn.negative && !this.lessThan(bn)) {
            BigNumber result = this.subtract(bn);
            result.negative = false;
            return result.toString();
        }
        else if(!this.negative && !bn.negative && this.lessThan(bn)) {
            BigNumber result = bn.subtract(this);
            result.negative = true;
            return result.toString();
        }
        else {
            BigNumber result = this.add(bn.abs());
            result.negative = false;
            return sciNot(result.toString());
        }
    }
    
    public String mul(BigNumber bn) {
        if(this.negative && bn.negative || !this.negative && !bn.negative) {
            BigNumber result = this.abs().multiply(bn.abs());
            result.negative = false;
            return sciNot(result.toString());
        }
        else {
            BigNumber result = this.abs().multiply(bn.abs());
            result.negative = true;
            return sciNot(result.toString());
        }
    }
    
    public String div(BigNumber bn) {
        if(this.negative && bn.negative || !this.negative && !bn.negative) {
            BigNumber result = this.abs().divide(bn.abs());
            result.negative = false;
            return sciNot(result.toString());
        }
        else {
            BigNumber result = this.abs().divide(bn.abs());
            result.negative = true;
            return sciNot(result.toString());
        }
    }
    
    public boolean equals(BigNumber bn)
    {  
        SLLNode digits1curr = this.digits1.getFirst();
        SLLNode digits1curr2 = bn.digits1.getFirst();
        SLLNode digits2curr = this.digits2.getFirst();
        SLLNode digits2curr2 = bn.digits2.getFirst();
        
        if((this.digits1.getLength() != bn.digits1.getLength()))
            return false; 
        else if((this.digits2.getLength() != bn.digits2.getLength()))
            return false;
        else if(this.negative ^ bn.negative)
            return false;
        else 
            while(digits1curr != null)
            {
                Character c = (Character)digits1curr.element;
                Character c2 = (Character)digits1curr2.element;
                
                if(c.compareTo(c2) != 0)
                    return false;
                else
                {
                digits1curr = digits1curr.snode;
                digits1curr2 = digits1curr2.snode;
                }
            }
           while(digits2curr != null)
            {
                Character c = (Character)digits2curr.element;
                Character c2 = (Character)digits2curr2.element;

                if(c.compareTo(c2) != 0)
                    return false;
                else
                {
                digits2curr = digits2curr.snode;
                digits2curr2 = digits2curr2.snode;
                }
            }
            return true;
    }
    
    public boolean greaterThan(BigNumber bn)
    {
        boolean isLarger = false;
        boolean isEqual = false;
        SLLNode digits1curr = this.digits1.getFirst();
        SLLNode digits1curr2 = bn.digits1.getFirst();
       
        if(bn.negative && !this.negative)
            return true;
        else if(this.negative && !bn.negative)
            return false;
        else if (this.digits1.getLength() > bn.digits1.getLength() && this.negative == true)
            return false;
        else if (this.digits1.getLength() > bn.digits1.getLength())
            return true;
        else if (this.digits1.getLength() < bn.digits1.getLength() && this.negative == true)
            return true;
        else if (this.digits1.getLength() < bn.digits1.getLength())
            return false;
        else if(this.equals(bn))
            return false;
        else
        {
            while(digits1curr != null)
            {
                Character c = (Character)digits1curr.element;
                Character c2 = (Character)digits1curr2.element;
                
                if(c.compareTo(c2) > 0)
                {
                    isLarger = true;
                    isEqual = false;
                }
                else if (c.compareTo(c2) == 0)
                {
                    isEqual = true;
                }
                else if(c.compareTo(c2) < 0)
                {
                    isLarger = false;
                    isEqual = false;         
                }         
                digits1curr = digits1curr.snode;
                digits1curr2 = digits1curr2.snode;                      
            }
            if(isLarger)
                return true;
            else if (!isLarger && !isEqual)
                return false;
            else
            {
                if(this.digits2.getLength() >= bn.digits2.getLength())
                {
                    for(int i = 0; i < bn.digits2.getLength(); i++)
                    {
                        SLLNode digits2curr = this.digits2.getFirst();
                        SLLNode digits2curr2 = bn.digits2.getFirst();
                        for(int j = 1; j < this.digits2.getLength() - i; j++)
                        {
                            digits2curr = digits2curr.snode;
                        }
                        for(int j = 1; j < bn.digits2.getLength() - i; j++)
                        {
                            digits2curr2 = digits2curr2.snode;
                        }
                        Character c = (Character)digits2curr.element;
                        Character c2 = (Character)digits2curr2.element;

                        if(c.compareTo(c2) > 0)
                        {
                            return true;
                        }
                        else if (c.compareTo(c2) == 0)
                        {
                            isEqual = true;
                        }
                        else if(c.compareTo(c2) < 0)
                        {
                            return false;         
                        }
                    }
                    return true;
                }
                else
                {
                    for(int i = 0; i < this.digits2.getLength(); i++)
                    {
                        SLLNode digits2curr = this.digits2.getFirst();
                        SLLNode digits2curr2 = bn.digits2.getFirst();
                        for(int j = 1; j < this.digits2.getLength() - i; j++)
                        {
                            digits2curr = digits2curr.snode;
                        }
                        for(int j = 1; j < bn.digits2.getLength() - i; j++)
                        {
                            digits2curr2 = digits2curr2.snode;
                        }
                        Character c = (Character)digits2curr.element;
                        Character c2 = (Character)digits2curr2.element;

                        if(c.compareTo(c2) > 0)
                        {
                            return true;
                        }
                        else if (c.compareTo(c2) == 0)
                        {
                            isEqual = true;
                        }
                        else if(c.compareTo(c2) < 0)
                        {
                            return false;         
                        }
                    }
                    return false;
                }
            }
        }
    }
    
    public boolean lessThan(BigNumber bn)
    {
        if(this.greaterThan(bn) == false && this.equals(bn) == false)
            return true;
        else
            return false;
    }   
}
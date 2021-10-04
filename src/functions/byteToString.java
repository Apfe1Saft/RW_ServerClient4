package functions;


public interface byteToString {
    default String working(byte[] buffer){
        String answer ;
        int k = 0;
        for(byte a : buffer){
            if(a!=0){
                k++;
            }
        }
        byte[] mass = new byte[k];
        if (k >= 0) System.arraycopy(buffer, 0, mass, 0, k);
        answer = new String(mass);
        return answer;
    }
}

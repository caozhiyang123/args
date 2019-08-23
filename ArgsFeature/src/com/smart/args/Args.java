package com.smart.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.smart.exception.ArgsException;
import com.smart.exception.ErrorCode;

public class Args
{
    private Map<Character,ArgumentMarshaler> marshalers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;
    
    public Args(String schema,String[] args) throws ArgsException{
        marshalers = new HashMap<Character,ArgumentMarshaler>();
        argsFound = new HashSet<Character>();
        
        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));
    }

    private void parseSchema(String schema)throws ArgsException{
        for (String ele : schema.split(","))
        {
            if(ele.length() > 0){
                parseSchemaEle(ele.trim());
            }
        }
    }

    private void parseSchemaEle(String ele)throws ArgsException{
        char eleId = ele.charAt(0);
        String eleTail = ele.substring(1);
        validateSchemaEleId(eleId);
        if(eleTail.length() == 0){
            marshalers.put(eleId, new BooleanArgumentMarshaler());
        }else if(eleTail.equals("*")){
             marshalers.put(eleId, new StringArgumentMarshaler());
        }else if(eleTail.equals("#")){
            marshalers.put(eleId, new IntegerArgumentMarshaler());
        }else if(eleTail.equals("##")){
            marshalers.put(eleId, new DoubleArgumentMarshaler());
        }else if(eleTail.equals("[*]")){
            marshalers.put(eleId, new StringArrayArgumentMarshaler());
        }else{
            throw new ArgsException(ErrorCode.INVALID_ARGUMENT_FORMAT,eleId,eleTail);
        }
    }

    private void validateSchemaEleId(char eleId)throws ArgsException{
        if(!Character.isLetter(eleId)){
            throw new ArgsException(ErrorCode.INVALID_ARGUMENT_NAME,eleId,null);
        }
    }
    
    private void parseArgumentStrings(List<String> asList)throws ArgsException{
        for (currentArgument = asList.listIterator() ; currentArgument.hasNext();)
        {
            String argString = currentArgument.next();
            System.out.println("args:"+argString);
            if(argString.startsWith("-")){
                parseArgumentCharacters(argString.substring(1));
            }else{
                continue;
            }
        }
    }

    private void parseArgumentCharacters(String argChars)throws ArgsException{
        for (int i = 0; i < argChars.length(); i++)
        {
            parseArgumentCharacter(argChars.charAt(i));
        }
    }

    private void parseArgumentCharacter(char argChar)throws ArgsException{
        ArgumentMarshaler m = marshalers.get(argChar);
        if(m == null){
            throw new ArgsException(ErrorCode.UNEXPECTED_ARGUMENT,argChar,null);
        }else{
            argsFound.add(argChar);
            try
            {
                m.set(currentArgument);
            } catch (ArgsException e)
            {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        }
    }

    public boolean has(char arg){
        return argsFound.contains(arg);
    }
    
    public int nextArgument(){
        return currentArgument.nextIndex();
    }
    
    public boolean getBoolean(char arg){
        return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
    }
    
    public String getString(char arg){
        return StringArgumentMarshaler.getValue(marshalers.get(arg));
    }
    
    public int getInt(char arg){
        return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
    }
    
    public String[] getStringArray(char arg){
        return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
    }
}

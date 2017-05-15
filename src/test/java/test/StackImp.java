package test;

class StackImp {
char arr[];
int top, size, len;


public StackImp(int n) {
size = n;
len = 0;
arr = new char[size];
top = -1;
}

public void push(char i) 
{

	if (top + 1 < size)
    arr[++top] = i;
	len++;

}

public boolean isEmpty() {

return top == -1;

}

public int pop() {
len--;
return arr[top--];
}


public void display() {
if (len == 0) {
System.out.print("Empty");
return;

}

for (int i = top; i >= 0; i--)
System.out.print(arr[i] + "\n");
System.out.println();

}

public void displayReverse() {
if (len == 0) {
System.out.print("Empty");
return;

}

for (int i = 0; i <= top; i++)
System.out.print(arr[i] + "\n");

}


public static void main(String[] args) {

StackImp aS = new StackImp(10);

aS.push('s');

aS.push('t');

aS.push('a');

aS.push('r');


System.out.println("Normal:");

aS.display();

System.out.println("Reverse:");

aS.displayReverse();

}

}

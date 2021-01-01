/*SHIVANGI PARASHAR(AI20MTECH14012)
--------------------------------------------------------------------------------------------------------------------------------------------------------
*/


/*creating my array list from scratch.
Here we are  not  having any fixed table_size  array  we will go on adding elements and will increase the capacity of array whenever array gets filled up.
We will keep track of the number of elements  that would be present at any particular point in the array.
*/
public class MyArrayList {
    private static int table_size;//would  specify the size of table array here
    private static final int CAPACITY = 10;//Initialized with some default capacity
    private int elements[];//elements[] is array in which we will add all elements inputs  given by user
    private int tracker;//Tracker  would help in keeping track of  number of elements being added by us


    public MyArrayList() {//constructor to initialize values of variables
        elements = new int[CAPACITY];
        tracker = 0;
        table_size = 1;//
    }
    //Adding   Integer in array if there is a capacity but if capacity gets filled up then we need to increase the table_size of the array.
    public void add(Integer e) {
        if (tracker == table_size)//If  array  gets filled up than we will increase it's capacity.
            increaseCapacity();//increaseCapacity function would increase capacity of array so that new elements can be accomodated.
        elements[tracker] = e;//add  element in O(1) time complexity.
        tracker++;//tracker count is incremented on addition of new element.
    }

    /*
     * If array gets filled up than we are increasing it's capacity If tracker
     * reaches the table size then we need to increase table size dynamically
     */
    private void increaseCapacity() {
        int increase_table[] = null;
        if (tracker == table_size) {
            increase_table = new int[table_size+1];//increase size of table
            {
                for (int i = 0; i < table_size; i++) {
                    increase_table[i] = elements[i];//added element by first increasing space
                }
            }
        }
        elements = increase_table;
        table_size = table_size +1;//whenever table gets filled  we will be increasing the size  of table by 1
    }
    //This function is responsible to change the size of table
    public void change_size() {
        int increase_table[] = null;
        if (tracker > 0) {
            increase_table = new int[tracker];
            for (int i = 0; i < tracker; i++) {
                increase_table[i] = elements[i];
            }
            table_size = tracker;
            elements = increase_table;
        }
    }

    //This function will return the element at ith location in Table
    public Integer get(int i) {
        if (i >= table_size || i < 0) {
            throw new IndexOutOfBoundsException("Index: out of bound happened " + i + " , table " + i);
        }
        return (Integer) elements[i];
    }
    //This  function returns the size of table
    public int table_size() {
        return table_size;
    }
    /*
     * Function to remove element from array. This function will take the element to
     * be deleted from array. It will find the first index of the element to be
     * deleted and would delete element based on index. Time complexity o(n)^2.
     */
    public boolean removeElement(Integer element) {


        for (int i = 0; i < elements.length; i++) {
            //If element to be deleted is found in the list
            if (elements[i] == element) {
                int index = i;//Find the index of the element if element is found
                if (index >= table_size)
                {
                    throw new IndexOutOfBoundsException("outOfBoundsMsg"+index);

                }
                else if(index==elements.length-1)
                {
                    removeAtEnD();
                }
                else
                {
                    remove(index);
                }

                return true;//return true if element to be deleted is found in the list

            }

        }
        return false;//return false if element to be deleted is not found in the list

    }

    public int  removeAtEnD() {//element would  be deleted based on index of element received
        if(tracker >0)
        {
            elements[tracker - 1] = 0;
        }
        return tracker--;//decrement tracker since number of elements present in list got decremented by 1

    }


    public int  remove(int ind) {//element would  be deleted based on index of element received
        for (int i = ind; i < tracker - 1; i++) {
            elements[i] = elements[i + 1];//shifting of elements after deleting
        }
        elements[tracker - 1] = 0;
        return tracker--;//decrement tracker since number of elements present in list got decremented by 1

    }


}

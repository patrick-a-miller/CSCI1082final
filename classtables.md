File read/write classes

| ObjectFile |
| ---------- |
| - filePath : String |
| - type : String |
| - openStatus : String |
||
|+ ObjectFile(path, type)|
|+ getType (): String|
|+ getPath (): String|
|+ isOpen(): Boolean|
|+ openFile() : Boolean|
|+ closeFile(): boolean|

Dictionary functionality (read-only) - the following extend ObjectFile

|DictionaryFile|
|----------|
|- isValid : Boolean|
|- format : String[]|
|- currentIndex : int|
|- entryCount : int|
|- dictionaryData : String[][]|
|- reader : Scanner |
|  |
|+ DictionaryFile(path, type, format)|
|+ getCurrentIndex (): int|
|+ getEntry(int) : String[]|
|+ nextEntry() : String[] |
|+ prevEntry() : String[] |
|- formatValid(String[]) : boolean|
|- loadDictionary() : boolean|

RoomDictionary|
|----------|
|-currentRoom : Room|
|  |
|+RoomDictionary(String,String)|
|+setCurrentRoom (int): void|
|+ searchRoomNumber(int) : Room[]|
|+ searchRoomCapacity(int) : Room[]|

|TeacherDictionary|
|----------|
|-currentTeacher : Teacher|
| |
|+TeacherDictionary(String,String)|
|+setCurrentTeacher (int): void|
|+searchTeacherID(String) : Teacher[]|
|+searchTeacherName(String): Teacher[]|
|+searchTeacherEmail(String): Teacher[]|

|ClassDictionary|
|----------|
|-currentClass : ClassEntry|
| |
|+ClassDictionary(string,string)|
|+setCurrentClass (int): void|
|+searchClassTeacher (Teacher) : ClassEntry|
|+searchClassTeacherString (String,String,String) : ClassEntry[]|
|+searchClassScheduleString(String,String,String) : ClassEntry[]|

Classes that use dictionary data

|Room|
|----------|
|-number : int|
|-capacity : int|
|-dictionaryIndex: int|
| |
|+Room (int,int,int)|
|+getNumber(): int|
|+getCapacity(): int|
|+getDictionaryIndex(): int|

|ClassEntry|
|----------|
|-classTitle: String|
|-teacher: Teacher|
|-studentCount: int|
|-studentList : Student[]|
|-classSchedule: Schedule|
|-dictionaryIndex: int|
| |
|+ClassEntry(String,Teacher,int,Student[],Schedule, int)|
|+getClassTitle(): String|
|+getTeacher(): Teacher|
|+getStudentCount(): int|
|+getStudentList() : Student[]|
|+getClassSchedule() : Schedule|
|+getDictionaryIndex(): int|




Data based on read-write file (extend objectfile)

|DataFile|
|----------|
|- isValid : boolean|
|- format : String[]|
|- stream : RandomAccessFile|
|- channel : FileChannel|
|- lock: FileLock|

|CurrentWindowTimeSlots|
|----------|
|- entryCount: int|
|- startPosition: int|
|- endPosition: int|
|- timeSlotArray: ArrayList|
| |
|+CurrentWindowTimeSlots(String,String,int)|
|+searchTimeSlotsDate(Date): int|
|+loadTimeSlots(int,int) : boolean|


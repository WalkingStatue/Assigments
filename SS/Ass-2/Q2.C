#include <conio.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char mnemonic[3][3][10] = {{"1", "START", "AD"}, {"2", "EQU", "AD"}};
char symbol_table[10][2][10] = {""};
int s1 = 0;
int main() {
  int i = 0, j = 0;
  int loc = 0;
  int start = 0, equ = 0, origin = 0;
  char *field, record[200], const1[10];
  char symb_loc[25];
  int n;
  char op[20];
  FILE *fr;
  clrscr();

  fr = fopen("Q_2.txt", "r");
  while (!feof(fr)) {
    int fcnt = 0;
    loc++;
    fgets(record, 200, fr);
    field = strtok(record, " ");

    while (field != NULL) {
      fcnt++;
      printf("%s \t", field);

      if (fcnt == 1) {
	if (strcmp(field, "#") != 0) {
	  strcpy(symbol_table[s1][0], field);
	  strcpy(op, field);
	  sprintf(symb_loc, "%d", loc);
          strcpy(symbol_table[s1][1], symb_loc);
          s1++;
        }
      }
      if (fcnt == 2) {
        int found = 0;
        int index;
        for (i = 0; i < 3; i++) {
          if (strcmp(mnemonic[i][1], field) == 0) {
            found = 1;
            index = i;
            break;
          }
        }
        if (found == 1)

        {
          char class1[10] = "";
          char mnemonic1[10] = "";
          strcpy(class1, mnemonic[index][2]);
          strcpy(mnemonic1, mnemonic[index][1]);
          if (strcmp(class1, "AD") == 0) {
            if (strcmp(mnemonic1, "START") == 0) {
              start = 1;
            }
            if (strcmp(mnemonic1, "EQU") == 0) {
              equ = 1;
              loc--;
            }
          }
        }
      }
      if (fcnt == 3) {
        if (start == 1) {
          strcpy(const1, field);
          loc = atoi(const1);
          loc = loc - 1;
          start = 0;
        }
        if (equ == 1) {
          char index_of_symbol[20];

          int find_index = 0;
          for (i = 0; i < s1; i++) {
            if (strcmp(symbol_table[i][0], field) == 0) {
              if (strcmp(symbol_table[i][1], " ") != 0) {
                find_index = 1;
                strcpy(index_of_symbol, symbol_table[i][1]);
                break;
              }
            }
          }
          if (find_index == 1) {
            for (i = 0; i < s1; i++) {
              if (strcmp(symbol_table[i][0], op) == 0) {
                strcpy(symbol_table[i][1], index_of_symbol);
                break;
              }
            }
            find_index = 0;
          }
          equ = 0;
        }
      }
      field = strtok(NULL, " ");
    }
  }

  fclose(fr);

  printf("\n \n \n symbol table\n");

  for (i = 0; i < s1; i++) {
    printf("\n");
    for (j = 0; j < 2; j++) {
      printf("%s \t", symbol_table[i][j]);
    }
  }
  getch();
  return 0;
}

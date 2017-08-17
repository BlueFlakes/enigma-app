#!/bin/bash

# INFO
# Run this script in the main directory of your project: where src/ folder is located


# SET THIS:
mainClass="App"
blacklist_suffix="#"

echo "Enigma Testing Script 0.1beta"
echo "NOTE: so far this script works only for ciphers that does not require keys"

# create python script to calculate edit distance
# from https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Python
echo "def levenshtein(s1, s2):
    if len(s1) < len(s2):
        return levenshtein(s2, s1)

    # len(s1) >= len(s2)
    if len(s2) == 0:
        return len(s1)

    previous_row = range(len(s2) + 1)
    for i, c1 in enumerate(s1):
        current_row = [i + 1]
        for j, c2 in enumerate(s2):
            insertions = previous_row[j + 1] + 1 # j+1 instead of j since previous_row and current_row are one character longer
            deletions = current_row[j] + 1       # than s2
            substitutions = previous_row[j] + (c1 != c2)
            current_row.append(min(insertions, deletions, substitutions))
        previous_row = current_row

    return previous_row[-1]
import sys
a = sys.argv[1]
b = sys.argv[2]
print(levenshtein(a,b))
" > levenshtein.py

#compile sources
if [ ! -d "bin" ]; then
	echo "creating bin/ directory..."
	mkdir bin
fi
echo "compiling sources..."
javac -d bin -sourcepath src/  src/**/*.java

# to make alias work in this script
shopt -s expand_aliases
# define an alias to run enigma
alias enigma="java -classpath bin/ enigma.${mainClass}"

# define tests
declare -a TEXTS=(
"to jest przykladowe zdanie do testu"
"can you encipher and decipher me"
"be nice to Scooby Doo otherwise he will be crying alone all night"
"he's not a dog he's a pussycat"
"wowowowow can you encipher numbers 123"
"WE DONT NEED NO EDUCATION"
)

# get all ciphers supported by enigma
declare -a CIPHERS=( $(enigma -l) )

#print all available ciphers
echo "available ciphers: ${CIPHERS[@]}"

# define ciphers by hand if -l option not available
# declare -a CIPHERS=(
# "ROT13"
# "CESAR"
# "VINEGRE"
# )

# initialize counters
ok=0
fail=0
error_rate=0
max_error_rate=0

echo "running tests..."

for text in "${TEXTS[@]}"; do
	echo \["$text"\]
	for cipher in "${CIPHERS[@]}"; do

		#skip blacklisted ciphers (those with given suffix)
		cipher_suffix=${cipher:${#cipher}-1:1}
		if [ ${blacklist_suffix} == ${cipher_suffix} ]; then
			break
		fi
		# run test and save the result to the variable
        echo "testing $cipher"
		output=$( echo "$text" | enigma -e "$cipher" | enigma -d "$cipher")
		if [ "$output" == "$text" ]
		then
			echo "OK" \["$cipher"\]
			((ok=ok+1))
		else
			echo "MISMATCH!" \["$cipher"\] - unexpected result: \["$output"\]
			((fail=fail+1))

			#calculate edit distance and update score
			dist=$(python levenshtein.py "$text" "$output")
			error_rate=$((error_rate+dist))
			max_error_rate=$((max_error_rate+${#text}))
		fi
	done

done

echo "SUCCESS: " "$ok"
echo "FAILURES: " "$fail"
echo "SCORE (ERROR RATE) (0-great "$max_error_rate"-disaster): " "$error_rate"/"$max_error_rate"

# JsonDecoder
Simple code I wrote to decode Json object recursively.

example:
{
	"A": {
		"B": {
			"C": val1
		}
		"C": val2
	}
}

For the above json, the program will output:
key: A.B.C value: val1
key: A.C value: val2

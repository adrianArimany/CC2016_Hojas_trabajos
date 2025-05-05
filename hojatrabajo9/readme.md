# Hoja Trabajo 9 - algoritmo de Huffman - compression y decompression

# Autores:

Adrian Arimany - 211063

Rodrigo Ajmac - 22279


Para correr el codigo de forma automatica:

Valla a root file donde esta el pom.xml file

```
java com.example.App test.txt
```

Esto genera los archivos de decompression y compression automaticamente.

Si usted quiere hacerlo manualmente, ingrese de la siguente manera:

Para comprimir

```
java com.example.App -c test.txt
```

Para decomprimir
```
java com.example.App -d test.txt.hufftree test.txt.huff
```

El algoritmo funciona si la el texto decomprimido es identico al texto original.

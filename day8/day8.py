import copy

def isAntenna(character):
  return character.islower() or character.isupper() or character.isnumeric()

def markLocation(mapLines, x, y):
  if(not isAntenna(mapLines[x][y])):
    mapLines[x] = mapLines[x][:y] + "#" + mapLines[x][y+1:]

def isLocationWithinTheMap(mapLines, x, y):
  return x >= 0 and y >= 0 and x < len(mapLines) and y < len(mapLines[x])

def getAntennasWithLocation(mapLines):
  antennasWithLocation = []
  for x, line in enumerate(mapLines):
    for y, character in enumerate(line):
      if isAntenna(character):
        antennasWithLocation.append({"frequency": character, "location": [x, y]})
  return antennasWithLocation

##########################################################################################

def findUniqueLocationsPart1(mapLines, showMarkedMap=False):
  antennasWithLocation = getAntennasWithLocation(mapLines)
  antinodeLocationMarkedLines = copy.deepcopy(mapLines)
  uniqueFrequencies = list({item["frequency"] for item in antennasWithLocation})
  uniqueAntinodeLocations = set()
  
  for frequency in uniqueFrequencies:
    locations = [antenna["location"] for antenna in antennasWithLocation if antenna["frequency"] == frequency]
    for location in locations:
      otherLocations = [otherLocation for otherLocation in locations if otherLocation != location]
      for otherLocation in otherLocations:
        x = location[0] - (otherLocation[0] - location[0])
        y = location[1] - (otherLocation[1] - location[1])
        if isLocationWithinTheMap(mapLines, x, y):
          markLocation(antinodeLocationMarkedLines, x, y)
          uniqueAntinodeLocations.add((x, y))

  if showMarkedMap:      
    print("Unique antinode locations marked map:")
    for line in antinodeLocationMarkedLines:
      print(line)

  return len(uniqueAntinodeLocations)


# TODO
def findUniqueLocationsPart2(mapLines, showMarkedMap=False):
  pass


file = open("input.txt", "r")
text = file.read()
mapLines = text.split("\n")

print("[Part 1] Unique antinode locations: ", findUniqueLocationsPart1(mapLines, True))

Circular_Progress_View
======================

Circular progress view for android


Circular Progress View

1. Implement a circular progress view, as depicted in Figure 1.
2. The background color of the progress view in RGB format is (0, 172, 237).
3. The current percentage of progress should be displayed as a whole number in the center
of the progress view. The color of the number in RGB format is (255, 255, 255).
4. A black ring around the number will provide a track for the progress bar. The color for the
black ring in RGB format is (0, 0, 0).
5. A green arc will be drawn around the number and over the black track, starting from the
12 o’clock position. The length of the arc is proportional to the progress percentage. Example: 0% means no visible green arc, while 100% means a full circle. The color for the green arc in RGB format is (0, 255, 22).
6. The view should be scalable and should display well in either portrait or landscape mode. Use anti­aliasing to achieve a smooth look.
 


Animation
1. The view will take in a goal number and a current number. The view will animate the green arc from the current number to the goal number. Ex: Current number = 50%, Goal number = 75%; green arc animates from 50% to 75%.
2. While animating the green arc, the percentage number in the middle will animate/count from the current number to the goal number.




# Smart Traffic Management System (Python version)

# Roads and vehicle counts
roads = ["North", "South", "East", "West"]
counts = []

# Input from user
for road in roads:
    count = int(input(f"Enter vehicle count for {road}: "))
    counts.append(count)

# Check if all zero
if sum(counts) == 0:
    print("\nNo vehicles detected. All signals stay Red.")
else:
    # Fixed slot times [40s, 30s, 20s, 10s]
    slots = [40, 30, 20, 10]
    times = [0] * 4
    order = []

    used = [False] * 4
    for s in range(4):
        best_idx = -1
        best_val = -1
        for i in range(4):
            if not used[i] and counts[i] > best_val:
                best_val = counts[i]
                best_idx = i
        used[best_idx] = True
        order.append(best_idx)
        times[best_idx] = slots[s]

    # Output
    print("\n🚦 Smart Traffic Signal Timings (Total cycle: 100s) 🚦")
    for i in range(4):
        print(f"{roads[i]} → {times[i]}s Green  ({counts[i]} vehicles)")

    print("\nPriority / Cycle order:")
    for s, idx in enumerate(order):
        print(f"{s+1}. {roads[idx]} ({counts[idx]} vehicles) → {slots[s]}s")
